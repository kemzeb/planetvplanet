package io.planetvplanet.controller;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path="planets")
public class SearchController {

    private final ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public Collection<SearchPlanetResult> searchForPlanets(
            @RequestParam String name,
            @RequestParam("exoplanet_flag") boolean exoplanetFlag) {

        return searchService.getPlanetsByNameSubstring(name, exoplanetFlag);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Planet> searchForPlanetByID(@PathVariable UUID id) {
        Optional<Planet> planetOptional = searchService.getPlanet(id);

        return planetOptional.isPresent() ?
                ResponseEntity.ok().body(planetOptional.get()) :
                ResponseEntity.ok().build();
    }

    @GetMapping(value = "random")
    public ResponseEntity<Planet> getRandomPlanet(@RequestParam("exoplanet_flag") Optional<Boolean> exoplanetFlag) {
        boolean flag = false;

        // If exoPlanetFlag is empty, we must determine whether we should
        // choose a planet or exoplanet. Since we are choosing at random,
        // lets randomize this choice as well.
        if(exoplanetFlag.isPresent()) {
            flag = exoplanetFlag.get();
        } else {
            int boolValue = new Random().nextInt() % 2;
            flag = boolValue == 1 ? true : false;
        }
        Optional<Planet> planetOptional = searchService.getRandomPlanet(flag);

        return planetOptional.isPresent() ?
                ResponseEntity.ok().body(planetOptional.get()) :
                ResponseEntity.ok().build();
    }
}
