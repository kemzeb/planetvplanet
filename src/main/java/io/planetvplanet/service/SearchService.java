package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {
  @Autowired PlanetRepository planetRepository;

  public SearchService(PlanetRepository planetRepository) {
    this.planetRepository = planetRepository;
  }

  @Override
  public Collection<SearchPlanetResult> getPlanetsByNameSubstring(
      String input, boolean isExoplanet) {
    Collection<SearchPlanetResult> resultCollection = new ArrayList<>();
    int limitSize = 10;

    if (input.length() >= 2) {
      // If any planets were found, create its corresponding DTO object.
      for (Planet planet :
          planetRepository.search(input, isExoplanet, PageRequest.ofSize(limitSize))) {
        resultCollection.add(new SearchPlanetResult(
            planet.getPlanetID(), planet.getPlanetName(), planet.getHostName()));
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
   * @param isExoplanet: Determine whether to choose a random exoplanet or
   *                     planet.
   * @return A random Optional object with a value, otherwise an empty
   * Optional.
   */
  @Override
  public Optional<Planet> getRandomPlanet(boolean isExoplanet) {
    long count = planetRepository.count();
    int numPlanetsSolarSys = 8;
    long totalPages = isExoplanet ? count - numPlanetsSolarSys : numPlanetsSolarSys;
    // Note: This randomization approach does not fair well when reaching values > 2^31.
    // I don't expect that we will find 2^31 exoplanets any time soon, but
    // imagining that we do, we will be missing out on a large portion of
    // the database if it scales to higher long values.
    int randomPageIdx = (int) (Math.random() * totalPages);
    Pageable pageable = PageRequest.of(randomPageIdx, 1);
    Page<Planet> planetPage = planetRepository.findByIsExoplanet(isExoplanet, pageable);

    return planetPage.isEmpty() ? Optional.empty() : planetPage.get().findFirst();
  }
}
