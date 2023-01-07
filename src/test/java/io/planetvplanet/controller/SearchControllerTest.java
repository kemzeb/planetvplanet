package io.planetvplanet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

import io.planetvplanet.model.Planet;
import io.planetvplanet.service.ISearchService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {
  @Mock private ISearchService searchService;

  @InjectMocks private SearchController underTest;

  @Test
  void searchForPlanetByID_givenEmptyRepo_returnValidResponse() {
    // Given
    ResponseEntity<Planet> response = null;

    // When
    when(searchService.getPlanetByFullName(any(String.class))).thenReturn(Optional.empty());
    response = underTest.searchForPlanetByFullName(new String());

    // Then
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void searchForPlanetByID_givenNonEmptyRepo_returnNonEmptyBody() {
    // Given
    ResponseEntity<Planet> response = null;
    Planet planet = new Planet("Kerwan", null, null, null, null, null, null, null, false);

    // When
    when(searchService.getPlanetByFullName(any(String.class))).thenReturn(Optional.of(planet));
    response = underTest.searchForPlanetByFullName(new String());

    // Then
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(planet);
  }

  @Test
  void getRandomPlanet_givenEmptyRepo_returnValidResponse() {
    // Given
    ResponseEntity<Planet> response = null;

    // When
    when(searchService.getRandomPlanet(anyBoolean())).thenReturn(Optional.empty());
    response = underTest.getRandomPlanet(Optional.empty());

    // Then
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void getRandomPlanet_givenNonEmptyRepo_returnNonEmptyBody() {
    // Given
    ResponseEntity<Planet> response = null;
    Planet planet = new Planet("Tabora", null, null, null, null, null, null, null, false);

    // When
    when(searchService.getRandomPlanet(anyBoolean())).thenReturn(Optional.of(planet));
    response = underTest.getRandomPlanet(Optional.empty());

    // Then
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(planet);
  }
}
