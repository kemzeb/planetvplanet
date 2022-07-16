package io.planetvplanet;

import io.planetvplanet.controller.SearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PlanetvplanetApplicationTests {

	@Autowired
	private SearchController searchController;

	@Test
	void contextLoads() {
		assertThat(searchController).isNotNull();
	}

}
