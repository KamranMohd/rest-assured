package com.restassured.basic;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetRequest {

	public static final String GOOGLE_API_KEY = "AIzaSyDw4OY0jpkgeyGPyz-ezE1DP_RUDFbaIjc";

	@BeforeClass
	public void setup() {
		// https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDhFe-kpBBuYds19YAmTTR7YoQTqkQeMGM
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}

	@Test(enabled=true)
	public void doStatusCodeVerification() {
		given()
			.param("origins", "Seattle")
			.param("units", "imperials")
			.param("destinations", "San Francisco")
			.param("key", GOOGLE_API_KEY)
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void captureResponseBody(){
		//	Using parameters map instead of passing values individually
		Map<String, String> map = new HashMap<>();
		map.put("origins", "Seattle");
		map.put("destinations", "San Francisco");
		map.put("key", GOOGLE_API_KEY);
		Response resp = given()
			.params(map)
		.when()
			.get("/distancematrix/json");
		System.out.println(resp.statusCode() + "\n"  + resp.body().prettyPrint());
	}
}
