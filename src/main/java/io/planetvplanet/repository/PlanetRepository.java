package io.planetvplanet.repository;

import io.planetvplanet.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, UUID> {
    Collection<Planet> findByPlanetNameIgnoreCaseContainingAndExoplanetFlag(String input, boolean exoplanetFlag);
}
