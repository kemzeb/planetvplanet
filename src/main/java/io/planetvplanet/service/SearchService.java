package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class SearchService implements ISearchService {
    @Autowired
    PlanetRepository planetRepository;

    public SearchService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    // FIXME: This maybe prone to an SQL injection attack. See if Spring JPA handles such attacks.
    @Override
    public Collection<SearchPlanetResult> getPlanetsByNameSubstring(String input, boolean exoplanetFlag) {
        Collection<SearchPlanetResult> resultCollection = new ArrayList<>();

        if(input.length() >= 2) {
            // If any planets were found, create its corresponding DTO object.
            for(Planet planet : planetRepository.findByPlanetNameIgnoreCaseContainingAndExoplanetFlag(input, exoplanetFlag)) {
                resultCollection.add(new SearchPlanetResult(planet.getPlanetID(),
                        planet.getPlanetName(), planet.getHostName()));
            }
        }

        return resultCollection;
    }

    @Override
    public Optional<Planet> getPlanet(UUID id) {
        return planetRepository.findById(id);
    }
}
