package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface ISearchService {
  /**
   * @param input: String or substring possibly representing a planet name.
   * @param isExoplanet: Search for exoplanets or planets of our Solar System.
   * @return Collection of SearchPlanetResult objects representing matching planet names.
   */
  Collection<SearchPlanetResult> getPlanetsByNameSubstring(String input, boolean isExoplanet);

  Optional<Planet> getPlanet(UUID id);

  Optional<Planet> getRandomPlanet(boolean isExoplanet);
}
