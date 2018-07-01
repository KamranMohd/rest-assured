package com.restassured.basic;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class GetRequest {
	
	@BeforeClass
	public void setup(){
		//https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDhFe-kpBBuYds19YAmTTR7YoQTqkQeMGM
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	public void doBasicGet(){
		given()
			.param("origins", "Seattle")
			.param("destinatios", "San Francisco")
			.param("key","AIzaSyDhFe-kpBBuYds19YAmTTR7YoQTqkQeMGM")
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200);
	}

}
