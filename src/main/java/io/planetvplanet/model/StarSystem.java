package io.planetvplanet.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StarSystem {
  @Id private String hostName;
  private Float distanceInParsecs;
  private Integer numStars;
  private Integer numPlanets;

  public StarSystem() {}

  public StarSystem(
      String hostName, Float distanceInParsecs, Integer numStars, Integer numPlanets) {
    this.hostName = hostName;
    this.distanceInParsecs = distanceInParsecs;
    this.numStars = numStars;
    this.numPlanets = numPlanets;
  }

  public String getHostName() {
    return hostName;
  }

  public Float getDistanceInParsecs() {
    return distanceInParsecs;
  }

  public Integer getNumStars() {
    return numStars;
  }

  public Integer getNumPlanets() {
    return numPlanets;
  }
}