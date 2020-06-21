package exercise7.utibank;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserLogin extends BaseRequest {

	@Test
	public void login() {

		//		https://uibank-api.azurewebsites.net/api/users/login - Post

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.body(new File("./data/UTILogin.json"))
				.post("api/users/login");

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		  idLogin = jsonPath.get("id");
		  userIdLogin = jsonPath.get("userId");
		System.out.println(" Id returned from Login : " + idLogin);
		System.out.println(" User Id returned from Login : " + userIdLogin);

	}

}
