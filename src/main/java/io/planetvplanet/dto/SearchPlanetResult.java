package io.planetvplanet.dto;

import java.util.UUID;

/**
 * Utilized in the Spring service layer, more specifically the search service,
 * to encapsulate much of the information of a Planet entity and have the
 * exposed information used within a response message.
 */
public class SearchPlanetResult {
  private UUID id;
  private String planetName;
  private String hostName;

  public SearchPlanetResult(UUID id, String planetName, String hostName) {
    this.id = id;
    this.planetName = planetName;
    this.hostName = hostName;
  }

  public UUID getId() {
    return id;
  }

  public String getPlanetName() {
    return planetName;
  }

  public String getHostName() {
    return hostName;
  }
}
