package io.planetvplanet.repository;

import io.planetvplanet.model.StarSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarSystemRepository extends JpaRepository<StarSystem, String> {}
