package io.planetvplanet.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Planet {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID planetID;
    private String planetName;
    private String hostName;
    private String discoveryYear;
    private String discoveryFacility;
    private String discoveryMethod;
    private Float orbitalPeriodDays;
    private Float planetEarthMass;
    private Float planetEarthRadius;
    private boolean isExoplanet;
    // TODO: Introduce additional tables to the database to avoid unnecessary redundancy.
    private Float systemDistanceInParsecs;
    private Integer systemNumStars;
    private Integer systemNumPlanets;

    public Planet() {}

    public Planet(String planetName, String hostName, String discoveryYear,
                  String discoveryFacility, String discoveryMethod, Float orbitalPeriodDays,
                  Float planetEarthMass, Float planetEarthRadius, boolean isExoplanet,
                  Float systemDistanceInParsecs, Integer systemNumStars, Integer systemNumPlanets) {
        this.planetID = UUID.randomUUID();
        this.planetName = planetName;
        this.hostName = hostName;
        this.discoveryYear = discoveryYear;
        this.discoveryFacility = discoveryFacility;
        this.discoveryMethod = discoveryMethod;
        this.orbitalPeriodDays = orbitalPeriodDays;
        this.planetEarthMass = planetEarthMass;
        this.planetEarthRadius = planetEarthRadius;
        this.isExoplanet = isExoplanet;
        this.systemDistanceInParsecs = systemDistanceInParsecs;
        this.systemNumStars = systemNumStars;
        this.systemNumPlanets = systemNumPlanets;
    }
    @Override
    public String toString() {
        return String.format("Planet: %s, Host: %s, Discovery Year: %s",
                this.planetName, this.hostName, this.discoveryYear);
    }

    public UUID getPlanetID() {
        return planetID;
    }

    public String getPlanetName() {
        return planetName;
    }

    public String getHostName() {
        return hostName;
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

    public Float getSystemDistanceInParsecs() {
        return systemDistanceInParsecs;
    }

    public Integer getSystemNumStars() {
        return systemNumStars;
    }

    public Integer getSystemNumPlanets() {
        return systemNumPlanets;
    }

    public boolean isExoplanet() {
        return isExoplanet;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
