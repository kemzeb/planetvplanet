package io.planetvplanet.controller;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path = "search")
public class SearchController {

    private final ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public Collection<SearchPlanetResult> searchForPlanets(
            @RequestParam String input,
            @RequestParam("exo_flag") boolean exoplanetFlag) {

        return searchService.getPlanetsByNameSubstring(input, exoplanetFlag);
    }

    @GetMapping(value = "planet")
    public ResponseEntity<Planet> searchForPlanetByID(@RequestParam UUID id) {
        Optional<Planet> planetOptional = searchService.getPlanet(id);

        return planetOptional.isPresent() ?
                ResponseEntity.ok().body(planetOptional.get()) :
                ResponseEntity.ok().build();
    }

    @GetMapping(value = "random")
    public ResponseEntity<Planet> getRandomPlanet(@RequestParam("exo_flag") boolean exoplanetFlag) {
        Optional<Planet> planetOptional = searchService.getRandomPlanet(exoplanetFlag);

        return planetOptional.isPresent() ?
                ResponseEntity.ok().body(planetOptional.get()) :
                ResponseEntity.ok().build();
    }
}
