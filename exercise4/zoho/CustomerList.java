package exercise4.zoho;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CustomerList extends BaseRequest{

	@Test(dependsOnMethods= {"exercise4.zoho.DeleteCustomer.deleteCustomer"})
	public void customerList() {
		Response response = (Response) RestAssured
				.given()
				.log().all()
				.headers(HeaderMap)
				.get();


		response.prettyPrint();	
		String custidArray;

		JsonPath jsonPath = response.jsonPath();
		List<Object> customerList = jsonPath.getList("customers");
		int listCount = customerList.size();
		for (int i=0 ; i<listCount; i++) {
			custidArray = jsonPath.get("customers["+i+"].customer_id");

			if (custidArray.equals(custId)){
				System.out.println(" Customers created : " + custidArray);

			}

		}

	}

}
