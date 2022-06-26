package io.planetvplanet.repository;

import io.planetvplanet.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findByPlanetNameIgnoreCaseContaining(String planetName);
}
