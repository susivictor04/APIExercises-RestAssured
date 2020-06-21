package exercise7.utibank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetLoanDetails extends BaseRequest {

	@Test(dependsOnMethods= {"exercise7.utibank.CreateLoan.applyLoan"})
	public void getLoanStatus() {

		//		https://uibank-api.azurewebsites.net/api/quotes/{{quote}}

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.get("api/quotes/" +createdQuoteID);

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		System.out.println(" Customer Income : " +  jsonPath.getString("income"));
		System.out.println(" Loan Amount : " +  jsonPath.getString("amount"));
		System.out.println(" Loan Term : " +  jsonPath.getString("term"));
		System.out.println("  Loan Rate : " +  jsonPath.getString("rate"));


	}

}
