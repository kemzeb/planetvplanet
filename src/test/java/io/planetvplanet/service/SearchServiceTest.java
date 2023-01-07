package io.planetvplanet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import io.planetvplanet.dto.PlanetSearchSuggestion;
import io.planetvplanet.model.Planet;
import io.planetvplanet.model.StarSystem;
import io.planetvplanet.repository.PlanetRepository;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
  @Mock private PlanetRepository planetRepository;

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
    verify(planetRepository, never()).search(eq(input), eq(false), any());
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
    verify(planetRepository, never()).search(input, false, Pageable.unpaged());
  }

  @Test
  void getPlanetsByNameSubstring_givenInputGreaterThan2AndNonEmptyRepo_returnEmptyCollection() {
    // Given
    String input = "ar";
    String planetName = "Aridia";
    String hostName = "Solana I";
    StarSystem system = new StarSystem(hostName, 0.0F, 0, 0);
    Planet planet = new Planet(planetName, system, null, null, null, null, null, null, false);

    // When
    when(planetRepository.search(eq(input), eq(false), any()))
        .thenReturn(new ArrayList<>(List.of(planet)));
    Collection<PlanetSearchSuggestion> planetCollection =
        underTest.getPlanetsByNameSubstring(input, false);

    // Then
    verify(planetRepository).search(eq(input), eq(false), any());
    assertThat(planetCollection).isNotNull();

    PlanetSearchSuggestion result = planetCollection.iterator().next();
    assertThat(result).isNotNull();
    assertThat(result.getPlanetName()).isEqualTo(planetName);
    assertThat(result.getHostName()).isEqualTo(hostName);
  }

  @Test
  void getPlanetByFullName_givenNameAndEmptyRepo_returnEmptyOptional() {
    // Given
    String planetName = "Daxx";

    // When
    when(planetRepository.findById(planetName)).thenReturn(Optional.empty());
    Optional<Planet> result = underTest.getPlanetByFullName(planetName);

    // Then
    verify(planetRepository).findById(planetName);
    assertThat(result).isEmpty();
  }

  @Test
  void getPlanetByFullName_givenNameAndNonEmptyRepo_returnNonEmptyOptional() {
    // Given
    Planet planet = new Planet();
    String planetName = planet.getPlanetName();

    // When
    when(planetRepository.findById(planetName)).thenReturn(Optional.of(planet));
    Optional<Planet> result = underTest.getPlanetByFullName(planetName);

    // Then
    verify(planetRepository).findById(planetName);
    assertThat(result).isPresent();
  }

  @Test
  void getRandomPlanet_givenEmptyRepo_returnEmptyOptional() {
    // Given
    when(planetRepository.findByIsExoplanet(anyBoolean(), any())).thenReturn(Page.empty());

    // When
    Optional<Planet> result = underTest.getRandomPlanet(false);

    // Then
    verify(planetRepository).findByIsExoplanet(eq(false), any());
    assertThat(result).isEmpty();
  }

  @Test
  void getRandomPlanet_givenNonEmptyRepo_returnNonEmptyOptional() {
    // Given
    Planet planet = new Planet();
    when(planetRepository.findByIsExoplanet(anyBoolean(), any()))
        .thenReturn(new PageImpl<>(List.of(planet)));

    // When
    Optional<Planet> result = underTest.getRandomPlanet(false);

    // Then
    verify(planetRepository).findByIsExoplanet(eq(false), any());
    assertThat(result).isPresent();
  }
}