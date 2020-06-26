package exercise8.tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetFenceTransition extends BaseRequest {

	//	https://api.tomtom.com/geofencing/1/fences/9b4fcb43-a42c-4598-80a5-e86a641deff1?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun


	@Test(dependsOnMethods= {"exercise8.tomtom.AddNewFence.addFence"})
	public void getFenceDetails() {

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.param("key",  key)
				.get("fences/" + fenceID);

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		String fID = jsonPath.get("id");

		if (fID.contains(fenceID)) {
			System.out.println("Newly Created fence is available");
		}

	}

}
