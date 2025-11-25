package com.boot.pokedex.search.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.boot.pokedex.search.engine.client.PokemonAPI;
import com.boot.pokedex.search.engine.dto.PokemanDTO;

@Service
public class PokeService {

	private final PokemonAPI pokemonAPI;
	private final Map<String, PokemanDTO> cache = new ConcurrentHashMap<>();
	private final long TTL = 10 * 60 * 1000; // 10 minutes
	private final Map<String, Long> cacheTime = new ConcurrentHashMap<>();

	public PokeService(PokemonAPI pokemonAPI) {
		this.pokemonAPI = pokemonAPI;
	}

	public PokemanDTO getPokemon(String name) {
		if (cache.containsKey(name)) {
			long time = cacheTime.get(name);
			if (System.currentTimeMillis() - time < TTL) {
				return cache.get(name);
			} else {
				cache.remove(name);
				cacheTime.remove(name);
			}
		}

		Map<String, Object> data;
		Map<String, Object> speciesData;
		try {
			data = pokemonAPI.getPokemon(name);
			speciesData = pokemonAPI.getPokemonSpecies(name);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch Pokémon data");
		}

		if (data == null || speciesData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon not found");
		}

		PokemanDTO dto = mapToDTO(data, speciesData);

		cache.put(name, dto);
		cacheTime.put(name, System.currentTimeMillis());

		return dto;
	}

	private PokemanDTO mapToDTO(Map<String, Object> data, Map<String, Object> speciesData) {
		PokemanDTO dto = new PokemanDTO();
		dto.setId((Integer) data.get("id"));
		dto.setName((String) data.get("name"));

		List<Map<String, Object>> typesList = (List<Map<String, Object>>) data.get("types");
		List<String> types = new ArrayList<>();
		for (Map<String, Object> t : typesList) {
			Map<String, Object> type = (Map<String, Object>) t.get("type");
			types.add((String) type.get("name"));
		}
		dto.setTypes(types);

		dto.setAbilities((List<Map<String, Object>>) data.get("abilities"));

		dto.setStats((List<Map<String, Object>>) data.get("stats"));

		dto.setSprites((Map<String, Object>) data.get("sprites"));

		// Moves (all moves)
		List<Map<String, Object>> movesList = (List<Map<String, Object>>) data.get("moves");
		List<String> moves = new ArrayList<>();
		for (Map<String, Object> moveMap : movesList) {
			Map<String, Object> move = (Map<String, Object>) moveMap.get("move");
			moves.add((String) move.get("name"));
		}
		dto.setMoves(moves);

		List<Map<String, Object>> flavorList = (List<Map<String, Object>>) speciesData.get("flavor_text_entries");
		for (Map<String, Object> entry : flavorList) {
			Map<String, Object> language = (Map<String, Object>) entry.get("language");
			if ("en".equals(language.get("name"))) {
				dto.setFlavorText(((String) entry.get("flavor_text")).replace("\n", " ").replace("\f", " "));
				break;
			}
		}

		return dto;
	}
}
