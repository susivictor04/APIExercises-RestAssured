package exercise4.zoho;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class CreateCustomer extends BaseRequest{

	@Test
	public void createCustomer() {


		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.body(new File("./data/customer.json"))
				.post();
		
		response.prettyPrint();
		System.out.println(" Response code :" + response.statusCode());

		JsonPath jsonPath = response.jsonPath();
		custId = jsonPath.get("customer.customer_id");

		System.out.println(" Customer created Id : " + custId);

	}


}
