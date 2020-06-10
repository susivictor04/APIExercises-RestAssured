package exercise3.bestbuy;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRegularSellingPrice {

	@Test
	public void getRegularSellingPrice() {

		//		Get the regular and selling price for iPhone11 Pro

		RestAssured.baseURI= "https://api.bestbuy.com/v1/";


		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("show","name,salePrice,regularPrice");
		parametersMap.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");
		parametersMap.put("pageSize","15");
		parametersMap.put("page","5");

		Response response = RestAssured
				.given()
				.header("Content-Type","application/json")
				.log().all()
				.contentType(ContentType.JSON)
				.params(parametersMap)
				.get("products(deviceManufacturer=apple&name=Apple - iPhone 11 Pro* )?format=json");

		response.prettyPrint();

		System.out.println(" Response code : " + response.getStatusCode());

	}

}
