package io.planetvplanet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Planet {
  @Id private String planetName;
  @ManyToOne @JoinColumn(name = "hostName") private StarSystem system;
  private String discoveryYear;
  private String discoveryFacility;
  private String discoveryMethod;
  private Float orbitalPeriodDays;
  private Float planetEarthMass;
  private Float planetEarthRadius;
  private boolean isExoplanet;

  public Planet() {}

  public Planet(String planetName, StarSystem system, String discoveryYear,
      String discoveryFacility, String discoveryMethod, Float orbitalPeriodDays,
      Float planetEarthMass, Float planetEarthRadius, boolean isExoplanet) {
    this.planetName = planetName;
    this.system = system;
    this.discoveryYear = discoveryYear;
    this.discoveryFacility = discoveryFacility;
    this.discoveryMethod = discoveryMethod;
    this.orbitalPeriodDays = orbitalPeriodDays;
    this.planetEarthMass = planetEarthMass;
    this.planetEarthRadius = planetEarthRadius;
    this.isExoplanet = isExoplanet;
  }

  @Override
  public String toString() {
    return String.format(
        "Planet: %s, Host: %s, Discovery Year: %s", this.planetName, this.discoveryYear);
  }

  public String getPlanetName() {
    return planetName;
  }

  public StarSystem getSystem() {
    return system;
  }

  public String getDiscoveryYear() {
    return discoveryYear;
  }

  public String getDiscoveryFacility() {
    return discoveryFacility;
  }

  public String getDiscoveryMethod() {
    return discoveryMethod;
  }

  public Float getOrbitalPeriodDays() {
    return orbitalPeriodDays;
  }

  public Float getPlanetEarthMass() {
    return planetEarthMass;
  }

  public Float getPlanetEarthRadius() {
    return planetEarthRadius;
  }

  public boolean isExoplanet() {
    return isExoplanet;
  }

  public void setPlanetName(String planetName) {
    this.planetName = planetName;
  }
}
