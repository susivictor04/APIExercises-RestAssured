package exercise3.bestbuy;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FindStoreNearPostcode {


	@Test
	public void findStore() {


		//Finding the store name, address and distance near to postal code 95125 - San Jose for product using SKU Code

		RestAssured.baseURI= "https://api.bestbuy.com/v1/products/6341359/stores.json?";

		//		products/6341359/stores.json?postalCode=95125&apiKey=qUh3qMK14GdwAs9bO59QRSCJ

		//		products(manufacturer=apple&search=iPhone 11 Pro)?format=json&show=name,regularPrice,salePrice&apiKey=qUh3qMK14GdwAs9bO59QRSCJ
		//      products(manufacturer=apple&search=iPhone 11 64GB&inStorePickup=true)+stores(region=RI)?format=json&show=sku,name,stores.storeId,stores.name,stores.address&apiKey=qUh3qMK14GdwAs9bO59QRSCJ

		// Map<String, String> parametersMap = new HashMap<String, String>();
		//parametersMap.put("products", "products/6341359/");




		Response response = RestAssured
				.given()
				.log().all()
				.accept(ContentType.JSON)
				.queryParam("postalCode","95125")
				.queryParam("apiKey","qUh3qMK14GdwAs9bO59QRSCJ")
				.get();

		response.prettyPrint();

		System.out.println(" Response code : " + response.getStatusCode());

	}

}
