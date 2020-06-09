package exercise1;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


@Test
public class CovidGetStats {

	public void getCovidStats() {

		String certificatesTrustStorePath = "C:/Program Files (x86)/Java/jre8/lib/security/cacerts/covidcert";
		System.setProperty("javax.net.ssl.keyStore", certificatesTrustStorePath);
		RestAssured.baseURI= "https://covid-19.dataflowkit.com/v1";
		String newCases, newDeathCases;

		Response response = RestAssured
				.given()
				.log().all()
				.accept(ContentType.JSON)
				.get();


		response.prettyPrint();

		ValidatableResponse validateResponse = response
				.then()
				.assertThat()
				.statusCode(200);


		JsonPath jsonPath = response.jsonPath();


		List<Map<Object, Object>> list = jsonPath.getList("");
		TreeMap<Object,Object> sortedList = new TreeMap<Object,Object>();
		TreeMap<Object,Object> sortedListNewDeathCases = new TreeMap<Object,Object>();


		//		to get the Top 5 Countries of the New Cases


		for (Map<Object, Object> eachValue : list) {

			if (eachValue.containsKey("New Cases_text") && eachValue.get("New Cases_text").toString().replaceAll("\\D","").length()>1) {

				newCases = eachValue.get("New Cases_text").toString().replaceAll("\\D","");

				sortedList.put(Integer.parseInt(newCases),eachValue.get("Country_text"));
			}

			if (eachValue.containsKey("New Deaths_text") && eachValue.get("New Deaths_text").toString().replaceAll("\\D","").length()>1) {

				newDeathCases = eachValue.get("New Deaths_text").toString().replaceAll("\\D","");

				sortedListNewDeathCases.put(Integer.parseInt(newDeathCases),eachValue.get("Country_text"));
			}


		}

		//to get the top 5 lowest death cases that has non zero deaths

		int size = sortedList.size();

		System.out.println(" Size of the List :" + size);

		int top=1;

		for (int i=size-2 ;i >=size-6 ; i--) {



			Object newCaseSorted= sortedList.keySet().toArray()[i];

			Object country= sortedList.values().toArray()[i];

			System.out.println( "Country : "+ country + " Top " + top + "  with New Cases : " + newCaseSorted); 

			System.out.println();

			top++;

		}

		System.out.println("**********************************");

		for (int i= 1 ; i<=5 ; i++) {

			Object newCaseDeathSorted= sortedListNewDeathCases.keySet().toArray()[i];

			Object countryDeathSorted= sortedListNewDeathCases.values().toArray()[i];

			System.out.println( "Country : "+ countryDeathSorted +  " Top " + i +" with Lowest Death Cases : " + newCaseDeathSorted);

			System.out.println();


		}

		if (response.getTime() < 600) {

			System.out.println("Time not exceeded 600MS");
		} 
		else {
			System.out.println("Time is exceeded 600MS");
		}

		if (response.contentType().equalsIgnoreCase("application/json")) {
			System.out.println("Content type is JSON");
		} 
		else {
			System.out.println("Content type is not JSON");
		}

	}
}

