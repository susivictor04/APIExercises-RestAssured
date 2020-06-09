package exercise1;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class CreateProduct {

//  to get multiple json files to create multiple products
	@DataProvider(name="getData", parallel=true)
	public String[] getDetails(){
		String[] data = new String[3];
		data[0]="C:\\Users\\Jane\\Desktop\\APIAutomation\\Exercises\\data\\product1.json";
		data[1]="C:\\Users\\Jane\\Desktop\\APIAutomation\\Exercises\\data\\product2.json";
		data[2]= "C:\\Users\\Jane\\Desktop\\APIAutomation\\Exercises\\data\\product3.json";
		return data;
	}

	@Test(dataProvider="getData")
	public void createPaypalProduct(String filename) {

		File jsonFile = new File(filename);
		
//		Get Request to get the list of Products

		RestAssured.baseURI= "https://api.sandbox.paypal.com/v1/catalogs/products";
		RestAssured.authentication= RestAssured.oauth2("A21AAGpDmLltrjso-MHH9KIIjcQZKqVvxpR33Tkpk617docUL_21yoSVVGdNw8ebVH4rW3MCc6OjYeFILd3j23gshyssZGryg");

		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jsonFile)
				.log().all()
				.post();

		response.prettyPrint();
		System.out.println(response.statusCode());
		JsonPath jsonPath = response.jsonPath();
		String prod = jsonPath.get("id");
		
//		Get Request to get the details the Category and type of the created Product

		Response prodResponse= RestAssured
				.given()
				.contentType(ContentType.JSON)
				.log().all()
				.get("/"+prod);

		prodResponse.prettyPrint();
		System.out.println(prodResponse.statusCode());
		
		
		JsonPath productJson = prodResponse.jsonPath();
		System.out.println("Product ID : " + productJson.get("id"));
		System.out.println("Product Type : " + productJson.get("type"));
		System.out.println("Product category : " + productJson.get("category"));
	}
}
