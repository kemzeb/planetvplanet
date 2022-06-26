package io.planetvplanet.helper;

import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVToDB {
    /**
     * Converts NASA's Planetary Systems Composite Data Table (a CSV file) into
     * records in a database.
     * @param repo: the planet repository bean
     * @param filePath: file path to the planet data table
     * @return list of planet entities
     */
    public static void convert(PlanetRepository repo, String filePath) throws IOException {
        Reader reader = new FileReader(filePath);
        CSVFormat csvFormatter = CSVFormat
                .DEFAULT
                .builder()
                .setCommentMarker('#')
                .setHeader()
                .build();
        CSVParser csvParser = CSVParser.parse(reader, csvFormatter);

        for(CSVRecord record : csvParser) {
            String planetName = record.get("pl_name");
            String hostName = record.get("hostname");
            String discoveryYear = record.get("disc_year");
            String discoveryFacility = record.get("disc_facility");
            String discoveryMethod = record.get("discoverymethod");

            Float orbitalPeriodDays = record.get("pl_orbper").isEmpty() ? null :
                    Float.parseFloat(record.get("pl_orbper"));
            Float earthMass = record.get("pl_bmasse").isEmpty() ? null :
                    Float.parseFloat(record.get("pl_bmasse"));
            Float earthRadius = record.get("pl_rade").isEmpty() ? null :
                    Float.parseFloat(record.get("pl_rade"));
            Float stellarTempKelvin = record.get("st_teff").isEmpty() ? null :
                    Float.parseFloat(record.get("st_teff"));
            Float stellarMass = record.get("st_mass").isEmpty() ? null :
                    Float.parseFloat(record.get("st_mass"));
            Float systemDistanceInParsecs = record.get("sy_dist").isEmpty() ? null :
                    Float.parseFloat(record.get("sy_dist"));

            Planet planet = new Planet(
                    planetName,
                    hostName,
                    discoveryYear,
                    discoveryFacility,
                    discoveryMethod,
                    orbitalPeriodDays,
                    earthMass,
                    earthRadius,
                    stellarTempKelvin,
                    stellarMass,
                    systemDistanceInParsecs);
            repo.save(planet);
        }
    }
}
