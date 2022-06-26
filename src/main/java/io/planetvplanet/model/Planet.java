package io.planetvplanet.model;

import javax.persistence.*;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long planetID;
    private String planetName;
    private String hostName;
    private String discoveryYear;
    private String discoveryFacility;
    private String discoveryMethod;
    private Float orbitalPeriodDays;
    private Float planetEarthMass;
    private Float planetEarthRadius;
    private Float stellarEffectiveTempKelvin;
    private Float stellarMass;
    private Float systemDistancesInParsecs;

    public Planet(String planetName, String hostName, String discoveryYear, String discoveryFacility,
                  String discoveryMethod, Float orbitalPeriodDays, Float planetEarthMass, Float planetEarthRadius,
                  Float stellarEffectiveTempKelvin, Float stellarMass, Float systemDistancesInParsecs) {
        this.planetName = planetName;
        this.hostName = hostName;
        this.discoveryYear = discoveryYear;
        this.discoveryFacility = discoveryFacility;
        this.discoveryMethod = discoveryMethod;
        this.orbitalPeriodDays = orbitalPeriodDays;
        this.planetEarthMass = planetEarthMass;
        this.planetEarthRadius = planetEarthRadius;
        this.stellarEffectiveTempKelvin = stellarEffectiveTempKelvin;
        this.stellarMass = stellarMass;
        this.systemDistancesInParsecs = systemDistancesInParsecs;
    }

    @Override
    public String toString() {
        return String.format("Planet: %s, Host: %s, Discovery Year: %s",
                this.planetName, this.hostName, this.discoveryYear);
    }

    public long getPlanetID() {
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

    public Float getStellarEffectiveTempKelvin() {
        return stellarEffectiveTempKelvin;
    }

    public Float getStellarMass() {
        return stellarMass;
    }

    public Float getSystemDistancesInParsecs() {
        return systemDistancesInParsecs;
    }



}
