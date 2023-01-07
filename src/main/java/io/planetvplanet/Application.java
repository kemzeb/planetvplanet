package io.planetvplanet;

import io.planetvplanet.helper.CSVToDB;
import io.planetvplanet.model.Planet;
import io.planetvplanet.model.StarSystem;
import io.planetvplanet.repository.PlanetRepository;
import io.planetvplanet.repository.StarSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

/**
 * This CLI runner is utilized to translate NASA's planetary data table from
 * its CSV format to database records that follow the typical CRUD operations
 * found in relational database engines.
 */
@Component
class DBCommandLineRunner implements CommandLineRunner {
  @Autowired private PlanetRepository planetRepo;
  @Autowired private StarSystemRepository systemRepo;

  @Override
  public void run(String... args) throws Exception {
    // Make sure that we have not already added records to the repository.
    if (planetRepo.count() == 0) {
      CSVToDB.convert(planetRepo, systemRepo, "src/main/resources/exoplanets.csv");
      saveSolarSystemPlanetsToDB();
    }
  }

  /**
   * Adds records into a database pertaining to planets in the Solar System.
   * Data values referenced from
   * <a href="https://nssdc.gsfc.nasa.gov/planetary/planetfact.html">
   * Nasa Planetary Fact Sheets
   * </a>. This method will be moved from this class in the future.
   *
   * Following the fact sheets, I use "sidereal orbit period" as the orbital
   * period and use "equatorial radius" as the planetary radius.
   */
  private void saveSolarSystemPlanetsToDB() {
    // 10^24 kilogram to Earth mass (since the fact sheet measures planetary
    // mass in (10^24 kilograms).
    final float E24KILOGRAM_TO_EARTH_MASS = 1.673360107095E-1f;
    final float KILOMETER_TO_EARTH_RADIUS = 1.56785E-4f;

    StarSystem system = new StarSystem("Sun", null, 1, 8);
    system = systemRepo.save(system);

    Planet planet = new Planet("Mercury", system, "Prehistoric", null, null, 87.969f,
        0.33010f * E24KILOGRAM_TO_EARTH_MASS, 2440.5f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Venus", system, "Prehistoric", null, null, 224.701f,
        4.8673f * E24KILOGRAM_TO_EARTH_MASS, 6051.8f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Earth", system, "n/a", null, "n/a", 365.256f, 1.0f, 1.0f, false);
    planetRepo.save(planet);

    planet = new Planet("Mars", system, "Prehistoric", null, null, 686.980f,
        0.64169f * E24KILOGRAM_TO_EARTH_MASS, 3396.2f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Jupiter", system, "Prehistoric", null, null, 4332.589f,
        1898.13f * E24KILOGRAM_TO_EARTH_MASS, 71492f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Saturn", system, "Prehistoric", null, null, 10759.22f,
        568.32f * E24KILOGRAM_TO_EARTH_MASS, 60268f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Uranus", system, "Prehistoric", null, null, 30685.4f,
        86.811f * E24KILOGRAM_TO_EARTH_MASS, 25559f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);

    planet = new Planet("Neptune", system, "Prehistoric", null, null, 60189f,
        102.409f * E24KILOGRAM_TO_EARTH_MASS, 24764f * KILOMETER_TO_EARTH_RADIUS, false);
    planetRepo.save(planet);
  }
}
