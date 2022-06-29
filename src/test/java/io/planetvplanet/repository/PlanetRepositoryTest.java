package io.planetvplanet.repository;

import io.planetvplanet.model.Planet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByPlanetNameIgnoreCaseContaining_onEmptyRepository_returnsEmptyCollection() {
        // Given
        String inputName = "";

        // When
        Collection<Planet> planetCollection = underTest
                .findByPlanetNameIgnoreCaseContaining(inputName);

        // Then
        assertThat(planetCollection).isNotNull().isEmpty();
    }

    @Test
    void findByPlanetNameIgnoreCaseContaining_onNonEmptyRepository_returnsNonEmptyCollection() {
        // Given
        String input = "Novalis";
        Planet planet = new Planet(input, null, null, null, null, null, null, null, false, null,
                null, null);
        underTest.save(planet);

        // When
        input = "nov"; // Should at least return the only element in the repo.
        Collection<Planet> planetCollection = underTest.findByPlanetNameIgnoreCaseContaining(input);

        // Then
        assertThat(planetCollection).isNotNull().hasSize(1);
    }
}