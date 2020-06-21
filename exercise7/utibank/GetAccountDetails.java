package exercise7.utibank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAccountDetails extends BaseRequest {

	@Test(dependsOnMethods= {"exercise7.utibank.UserLogin.login"})
	public void getAccountDetails() {

		//		https://uibank-api.azurewebsites.net/api/accounts?filter[where][userId]=5eee0d92e29f950044ba2f75

		System.out.println(idLogin + "  " + userIdLogin );

		Response  response  =  RestAssured

				.given()
				.headers(HeaderMap)
				.header("Authorization", idLogin)
				.log().all()
				.queryParam("filter[where][userId]",userIdLogin)
				.get("api/accounts");

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		System.out.println(" Account Number : " +  jsonPath.getString("accountNumber"));
		System.out.println(" Account Holder Name : " +  jsonPath.getString("friendlyName"));
		System.out.println(" Account Type : " +  jsonPath.getString("type"));
		System.out.println(" Account Balance :  " +  jsonPath.getString("balance"));


	}
}