package com.boot.pokedex.search.engine.dto;

import java.util.List;
import java.util.Map;

public class PokemanDTO {
	private int id;
	private String name;
	private List<String> types;
	private List<Map<String, Object>> abilities;
	private List<Map<String, Object>> stats;
	private Map<String, Object> sprites;
	private List<String> moves;
	private String flavorText;

	public PokemanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<Map<String, Object>> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Map<String, Object>> abilities) {
		this.abilities = abilities;
	}

	public List<Map<String, Object>> getStats() {
		return stats;
	}

	public void setStats(List<Map<String, Object>> stats) {
		this.stats = stats;
	}

	public Map<String, Object> getSprites() {
		return sprites;
	}

	public void setSprites(Map<String, Object> sprites) {
		this.sprites = sprites;
	}

	public List<String> getMoves() {
		return moves;
	}

	public void setMoves(List<String> moves) {
		this.moves = moves;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

}
