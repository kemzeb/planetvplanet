package io.planetvplanet.repository;

import io.planetvplanet.model.Planet;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, UUID> {
  // FIXME: This maybe prone to an SQL injection attack. See if Spring JPA handles such attacks.
  @Query("SELECT p FROM Planet p WHERE UPPER(p.planetName) LIKE UPPER(CONCAT('%', :input, '%')) "
      + "AND p.isExoplanet=:isExoplanet")
  List<Planet>
  search(
      @Param("input") String input, @Param("isExoplanet") boolean isExoplanet, Pageable pageable);

  /**
   * Method attended to be used to find a random row in the planet records.
   */
  Page<Planet> findByIsExoplanet(boolean isExoplanet, Pageable pageable);
}
