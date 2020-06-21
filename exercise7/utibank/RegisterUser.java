package exercise7.utibank;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RegisterUser extends BaseRequest {


	@Test
	public void createUser() {

		//		https://uibank-api.azurewebsites.net/api/users/


		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.body(new File("./data/UTICreateCustomer.json"))
				.post("api/users");

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();

		createUserId = jsonPath.get("id");
		emailID = jsonPath.get("email");

		System.out.println(" Created User Id in Register User Method : " + createUserId );
		System.out.println(" emailID in Register User Method : " + emailID);

	}

}


