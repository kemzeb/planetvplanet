package io.planetvplanet.helper;

import io.planetvplanet.model.Planet;
import io.planetvplanet.repository.PlanetRepository;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
    CSVFormat csvFormatter = CSVFormat.DEFAULT.builder().setCommentMarker('#').setHeader().build();
    CSVParser csvParser = CSVParser.parse(reader, csvFormatter);

    for (CSVRecord record : csvParser) {
      String planetName = getStringFromCSVRecord("pl_name", record);
      String hostName = getStringFromCSVRecord("hostname", record);
      String discoveryYear = getStringFromCSVRecord("disc_year", record);
      String discoveryFacility = getStringFromCSVRecord("disc_facility", record);
      String discoveryMethod = getStringFromCSVRecord("discoverymethod", record);

      Float orbitalPeriodDays = getFloatFromCSVRecord("pl_orbper", record);
      Float earthMass = getFloatFromCSVRecord("pl_bmasse", record);
      Float earthRadius = getFloatFromCSVRecord("pl_rade", record);
      Float systemDistanceInParsecs = getFloatFromCSVRecord("sy_dist", record);
      Integer numStars = getIntFromCSVRecord("sy_snum", record);
      Integer numPlanets = getIntFromCSVRecord("sy_pnum", record);

      Planet planet = new Planet(planetName, hostName, discoveryYear, discoveryFacility,
          discoveryMethod, orbitalPeriodDays, earthMass, earthRadius, true, systemDistanceInParsecs,
          numStars, numPlanets);
      repo.save(planet);
    }
  }

  private static String getStringFromCSVRecord(String column, CSVRecord record) {
    return record.get(column);
  }

  private static Float getFloatFromCSVRecord(String column, CSVRecord record) {
    String value = record.get(column);
    return value.isBlank() ? null : Float.parseFloat(value);
  }

  private static Integer getIntFromCSVRecord(String column, CSVRecord record) {
    String value = record.get(column);
    return value.isBlank() ? null : Integer.parseInt(value);
  }
}
