package exercise8.tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RegenerateAdminKey extends BaseRequest {

	//	https://api.tomtom.com/geofencing/1/regenerateKey?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun

	@Test	
	public void regenerateAdminKey() {

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.queryParam("key",  key)
				.body("{\r\n" + 
						"  \"secret\": \"secret_key\"\r\n" + 
						"}")
				.post("regenerateKey");
		
		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();

		adminKey = jsonPath.get("adminKey");
		
		System.out.println("Admin Key created : " + adminKey);

	

	}

}
