package exercise5.hotelbeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RoomBooking extends CreateSignature {
	
	

	@Test(dependsOnMethods= {"exercise5.hotelbeds.CheckRoomAvailability.checkAvailability"})
	public void roomBooking() {


		//		https://api.test.hotelbeds.com/hotel-api/1.0/bookings  

		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.body("{\r\n" + 
						"    \"holder\": {\r\n" + 
						"        \"name\": \"Georgina\",\r\n" + 
						"        \"surname\": \"Priest\"\r\n" + 
						"    },\r\n" + 
						"    \"rooms\": [\r\n" + 
						"        {\r\n" + 
						"            \"rateKey\":\""+firstBookableRoomRate+ "\",\r\n" + 
						"            \"paxes\": [\r\n" + 
						"                {\r\n" + 
						"                    \"roomId\": \"1\",\r\n" + 
						"                    \"type\": \"AD\",\r\n" + 
						"                    \"name\": \"Georgina\",\r\n" + 
						"                    \"surname\": \"Priest\"\r\n" + 
						"                }\r\n" + 
						"            ]\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"clientReference\": \"IntegrationAgency\",\r\n" + 
						"    \"remark\": \"Booking remarks are to be written here.\",\r\n" + 
						"	\"tolerance\" : 9.00\r\n" + 
						"    }")
				.post("bookings");


		response.prettyPrint();
		System.out.println("Response code is :" + response.getStatusCode());

		JsonPath bookingJsonPath = response.jsonPath();

		bookingRef = bookingJsonPath.get("booking.reference");
		
		System.out.println(" Booking Reference : " +bookingRef );

	}

}
