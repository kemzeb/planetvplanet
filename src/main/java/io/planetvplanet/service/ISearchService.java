package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ISearchService {
    /**
     * @param input: String or substring possibly representing a planet name.
     * @param exoplanetFlag: Search for exoplanets or planets of our Solar System.
     * @return Collection of SearchPlanetResult objects representing matching planet names.
     */
    Collection<SearchPlanetResult> getPlanetsByNameSubstring(String input, boolean exoplanetFlag);

    Optional<SearchPlanetResult> getPlanetByID(UUID id);
}
