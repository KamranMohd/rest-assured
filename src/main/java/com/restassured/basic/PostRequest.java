package com.restassured.basic;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PostRequest {

	public static final String GOOGLE_API_KEY = "AIzaSyBxYJV_6ofFXDhw5G_NKShFq-w55bJSxV4";

	@BeforeClass
	public void setup() {
		// https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDhFe-kpBBuYds19YAmTTR7YoQTqkQeMGM
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}

	@Test(enabled=true)
	public void addPlace() {
		given()
		// If we add .param(), it will throw saying that we can either pass
		//	form parameter or body parameter
			.queryParam("key",GOOGLE_API_KEY)
			.body("{"
					+ "\"location\": {"
					+ "\"lat\": -33.8669710,"
					+ "\"lng\": 151.1958750"
					+ "},"
					+ "\"accuracy\": 50,"
					+ "\"name\": \"Google Shoes!\","
					+ "\"phone_number\": \"(02) 9374 4000\","
					+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
					+ "\"types\": [\"shoe_store\"],"
					+ "\"website\": \"http://www.google.com.au/\","
					+ "\"language\": \"en-AU\""
					+ "}")
		.when()
			.post("/place/add/json")
		.then()
			.statusCode(200);
	}
}
