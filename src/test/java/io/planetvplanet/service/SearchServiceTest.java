package io.planetvplanet.service;

import io.planetvplanet.dto.SearchPlanetResult;
import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    private SearchService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SearchService(planetRepository);
    }

    @Test
    void getPlanetsByNameSubstring_withEmptyInput_returnEmptyCollection() {
        // Given
        String input = "";

        // When
        underTest.getPlanetsByNameSubstring(input, false);

        // Then
        verify(planetRepository, never()).findByPlanetNameIgnoreCaseContaining(input);
    }

    @Test
    void getPlanetsByNameSubstring_givenSingleCharInputAndNonEmptyRepo_returnEmptyCollection() {
        // Given
        String input = "A";
        Planet planet = new Planet();

        planet.setPlanetName("Aridia");

        // When
        underTest.getPlanetsByNameSubstring(input, false);

        // Then
        verify(planetRepository, never()).findByPlanetNameIgnoreCaseContaining(input);
    }

    @Test
    void getPlanetsByNameSubstring_givenInputGreaterThan2AndNonEmptyRepo_returnEmptyCollection() {
        // Given
        String input = "ar";
        String planetName = "Aridia";
        String hostName = "Solana I";
        Planet planet = new Planet();

        planet.setPlanetName(planetName);
        planet.setHostName(hostName);
        when(planetRepository.findByPlanetNameIgnoreCaseContaining(input)).thenReturn(
                new ArrayList<>(List.of(planet)));

        // When
        Collection<SearchPlanetResult> planetCollection =
                underTest.getPlanetsByNameSubstring(input, false);

        // Then
        verify(planetRepository).findByPlanetNameIgnoreCaseContaining(input);
        assertThat(planetCollection).isNotNull();

        SearchPlanetResult result = planetCollection.iterator().next();
        assertThat(result).isNotNull();
        assertThat(result.getPlanetName()).isEqualTo(planetName);
        assertThat(result.getHostName()).isEqualTo(hostName);
    }


    @Test
    void getPlanetByID_givenIDAndEmptyRepo_returnEmptyOptional() {
        // Given
        UUID id = UUID.randomUUID();
        when(planetRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<SearchPlanetResult> result = underTest.getPlanetByID(id);

        // Then
        verify(planetRepository).findById(id);
        assertThat(result).isEmpty();
    }

    @Test
    void getPlanetByID_givenIDAndNonEmptyRepo_returnNonEmptyOptional() {
        // Given
        Planet planet = new Planet();
        UUID id = planet.getPlanetID();
        when(planetRepository.findById(id)).thenReturn(Optional.of(planet));

        // When
        Optional<SearchPlanetResult> result = underTest.getPlanetByID(id);

        // Then
        verify(planetRepository).findById(id);
        assertThat(result).isPresent();
    }
}