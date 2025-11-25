package com.boot.pokedex.search.engine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.pokedex.search.engine.dto.PokemanDTO;
import com.boot.pokedex.search.engine.service.PokeService;

@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin(origins = "*")
public class PokemonController {

	private final PokeService pokeService;

	public PokemonController(PokeService pokeService) {
		this.pokeService = pokeService;
	}

	@GetMapping("/{name}")
	public PokemanDTO getPokemon(@PathVariable String name) {
		PokemanDTO dto = pokeService.getPokemon(name);
		if (dto == null) {
			throw new RuntimeException("Pokémon not found: " + name);
		}
		return dto;
	}
}
