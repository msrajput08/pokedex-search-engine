import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [name, setName] = useState("");
  const [pokemon, setPokemon] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    if (!name) return;
    setLoading(true);
    setError("");
    try {
      const res = await axios.get(`http://localhost:8080/api/pokemon/${name.toLowerCase()}`);
      setPokemon(res.data);
    } catch {
      setError("Pokémon not found!");
      setPokemon(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-container">
      <div className="overlay">
        <h1 className="title">Pokédex</h1>

        <div className="search-bar">
          <input
            type="text"
            placeholder="Enter Pokémon name..."
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>

        {loading && <p className="loading">Loading...</p>}
        {error && <p className="error">{error}</p>}

        {pokemon && (
          <div className="card">
            <h2 className="name">{pokemon.name}</h2>

            <div className="sprites">
              {Object.entries(pokemon.sprites).map(
                ([key, url]) => url && <img key={key} src={url} alt={key} className="sprite" />
              )}
            </div>

            <div className="type">
              {pokemon.types.map((t) => (
                <span key={t} className="type-card">{t}</span>
              ))}
            </div>

            <div className="abilities">
              <h3>Abilities</h3>
              <div className="ability">
                {pokemon.abilities.map((a, i) => (
                  <span key={i} className={`ability-tag ${a.isHidden ? "hidden" : ""}`}>
                    {a.ability.name}
                  </span>
                ))}
              </div>
            </div>

            <div className="stats">
              <h3>Stats</h3>
              {pokemon.stats.map((s, i) => (
                <div key={i} className="bar-chart">
                  <span className="stat-name">{s.stat.name.toUpperCase()}</span>
                  <div className="bar-bg">
                    <div className="bar-fill" style={{ width: `${s.base_stat}%` }}></div>
                  </div>
                  <span className="stat-value">{s.base_stat}</span>
                </div>
              ))}
            </div>

            <div className="moves">
              <h3>Moves</h3>
              <div className="moves-card">
                {pokemon.moves.map((m, i) => (
                  <span key={i} className="move-tag">{m}</span>
                ))}
              </div>
            </div>

            <p className="flavor-text">{pokemon.flavorText}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
