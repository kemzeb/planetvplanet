package io.planetvplanet.dto;

/**
 * Utilized in the Spring service layer, more specifically the search service,
 * to encapsulate much of the information of a Planet entity and have the
 * exposed information used within a response message.
 */
public class SearchPlanetResult {
  private String planetName;
  private String hostName;

  public SearchPlanetResult(String planetName, String hostName) {
    this.planetName = planetName;
    this.hostName = hostName;
  }

  public String getPlanetName() {
    return planetName;
  }

  public String getHostName() {
    return hostName;
  }
}
