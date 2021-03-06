package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService implements ISearchService {
    @Autowired
    PlanetRepository planetRepository;

    public SearchService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public Collection<SearchPlanetResult> getPlanetsByNameSubstring(String input, boolean exoplanetFlag) {
        Collection<SearchPlanetResult> resultCollection = new ArrayList<>();
        int limitSize = 10;

        if(input.length() >= 2) {
            // If any planets were found, create its corresponding DTO object.
            for(Planet planet : planetRepository.search(input, exoplanetFlag, PageRequest.ofSize(limitSize))) {
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

    /**
     * Grab a random Planet object from the data layer.
     * @param exoplanetFlag: Determine whether to choose a random exoplanet or
     *                     planet.
     * @return A random Optional object with a value, otherwise an empty
     * Optional.
     */
    @Override
    public Optional<Planet> getRandomPlanet(boolean exoplanetFlag) {
        long count = planetRepository.count();
        int numPlanetsSolarSys = 8;
        long totalPages = exoplanetFlag ? count - numPlanetsSolarSys : numPlanetsSolarSys;
        // Note: This randomization approach does not fair well when reaching values > 2^31.
        // I don't expect that we will find 2^31 exoplanets any time soon, but
        // imagining that we do, we will be missing out on a large portion of
        // the database if it scales to higher long values.
        int randomPageIdx = (int) (Math.random() * totalPages);
        Pageable pageable = PageRequest.of(randomPageIdx, 1);
        Page<Planet> planetPage = planetRepository.findByExoplanetFlag(exoplanetFlag, pageable);

        return planetPage.isEmpty() ? Optional.empty() : planetPage.get().findFirst();
    }
}
