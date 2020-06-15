package exercise5.hotelbeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CheckRoomRates extends CreateSignature {

	@Test(dependsOnMethods= {"exercise5.hotelbeds.CheckRoomAvailability.checkAvailability"})
	public void getCheckRates() {

		//		https://api.test.hotelbeds.com/hotel-api/1.0/checkrates   

		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.body("{\r\n" + 
						"    \"rooms\": [\r\n" + 
						"        {\r\n" + 
						"            \"rateKey\": \"" +firstBookableRoomRate+ "\"\r\n" + 
						"        }\r\n" + 
						"    ]\r\n" + 
						"}")
				.post("checkrates");

		response.prettyPrint();
		System.out.println("Response code is :" + response.getStatusCode());

	}



}
