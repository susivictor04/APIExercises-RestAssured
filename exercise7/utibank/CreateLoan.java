package exercise7.utibank;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateLoan extends BaseRequest {


	@Test(dependsOnMethods= {"exercise7.utibank.RegisterUser.createUser"})
	public void applyLoan() {

		//		https://uibank-api.azurewebsites.net/api/quotes/newquote

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.body("{\r\n" + 
						"    \"email\": \""+ emailID + "\",\r\n" + 
						"    \"amount\": 10000,\r\n" + 
						"    \"term\": 3,\r\n" + 
						"    \"income\": 5000,\r\n" + 
						"    \"age\": 30\r\n" + 
						"}")
				.post("api/quotes/newquote");

		response.prettyPrint();
		JsonPath jsonPath = response.jsonPath();
		createdQuoteID = jsonPath.get("quoteid");
		System.out.println(" Created Quote ID : " + createdQuoteID);


	}

}
