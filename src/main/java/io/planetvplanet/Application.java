package io.planetvplanet;

import io.planetvplanet.helper.CSVToDB;
import io.planetvplanet.repository.PlanetRepository;
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

	@Autowired
	private PlanetRepository planetRepo;

	@Override
	public void run(String... args) throws Exception {
		// Make sure that we have not already added records to the repository.
		if(planetRepo.count() == 0) {
			CSVToDB.convert(planetRepo, "src/main/resources/exoplanets.csv");
		}
	}
}
