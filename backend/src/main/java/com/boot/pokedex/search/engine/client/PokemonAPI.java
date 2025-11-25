package com.boot.pokedex.search.engine.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PokemonAPI {

	private WebClient webClient;

	public PokemonAPI() {
		this.webClient = WebClient.builder().baseUrl("https://pokeapi.co/api/v2").build();
	}

	public Map<String, Object> getPokemon(String name) {
		try {
			return webClient.get().uri("/pokemon/{name}", name.toLowerCase()).retrieve().bodyToMono(Map.class).block();
		} catch (RuntimeException e) {
			return null;
		}

	}

	public Map<String, Object> getPokemonSpecies(String name) {
		try {
			return webClient.get().uri("/pokemon-species/{name}", name.toLowerCase()).retrieve().bodyToMono(Map.class)
					.block();
		} catch (RuntimeException e) {
			return null;

		}
	}
}
