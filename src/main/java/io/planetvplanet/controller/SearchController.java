package io.planetvplanet.controller;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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
}
