package org.zetrahytes.restapi;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zetrahytes.restapi.app.Main;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class TodoResourceTest {

	private HttpServer server;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();

		// setup restassured
		RestAssured.baseURI = Main.BASE_URI;
		RestAssured.config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
		RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON).addFilter(new RequestLoggingFilter(LogDetail.ALL, true, System.out))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL, true, System.out)).build();
	}

	@After
	public void tearDown() throws Exception {
		server.shutdown();
	}

	@Test
	public void testListTodos() {
		get("/todos").then().statusCode(200).and().assertThat().body("name", hasItems("add checkstyle"));
	}
}
