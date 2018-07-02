package com.restassured.basic;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.model.AddPlace;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequestWithPOJO {
	public static final String GOOGLE_API_KEY = "AIzaSyBxYJV_6ofFXDhw5G_NKShFq-w55bJSxV4";

	@BeforeClass
	public void setup() {
		// https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDhFe-kpBBuYds19YAmTTR7YoQTqkQeMGM
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}

	@Test(enabled = true)
	public void addPlace() {
		Map<String, Double> loc = new HashMap<>();
		loc.put("lat", -33.8669710);
		loc.put("lng", 151.1958750);

		List<String> list = new ArrayList<>();
		list.add("shoe_store");

		AddPlace place = new AddPlace();
		place.setLocation(loc);
		place.setAccuracy(50);
		place.setName("Google Shoes!");
		place.setPhone_number("(02) 9374 4000");
		place.setAddess("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
		place.setTypes(list);
		place.setWebsite("http://www.google.com.au");
		place.setLanguage("en-AU");

		//	This serialization process will not be possible with jackson databind jar file
		//	which is added as dependencies in the gradle
		Response resp = given()
			.queryParam("key", GOOGLE_API_KEY)
			.contentType(ContentType.JSON)
			.body(place)
		.when()
			.post("/place/add/json");
		System.out.println(resp.getBody().prettyPrint());
	}
}
