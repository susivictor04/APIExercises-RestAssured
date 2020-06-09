package exercise2;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MajorCitiesWeather {

	@DataProvider(name="citiesfromJson", parallel=true)
	public String[] getCityName() throws IOException, ParseException {

		String[] cityNames = null ;
		JSONParser jsonParser = new JSONParser();
		FileReader filereader =  new FileReader("C:\\Users\\Jane\\Desktop\\APIAutomation\\Exercises\\data\\citylist.json");
		Object jsonObj = jsonParser.parse(filereader);
		JSONArray cityList = (JSONArray)jsonObj;
		int size = cityList.size();


		cityNames = new String[size];
		for (int i =0 ;i< cityList.size(); i++) {

			JSONObject cityData = (JSONObject) cityList.get(i);

			if ((cityData.get("country").equals("IN")) && (cityData.get("name")!=null)) {
				cityNames[i] = (String) cityData.get("name");
				System.out.println("City Name is  :" + cityNames[i]);
			}
			else
				continue;

		}
		return (String[]) cityNames;

	}

	@Test(dataProvider="citiesfromJson")
	public void getCityWeather(String city) {

		RestAssured.baseURI= "http://api.openweathermap.org/data/2.5/weather";

		Response response = RestAssured
				.given()
				//.log().all()
				.queryParam("q", city +"," +"IN")
				.queryParam("appid", "c397236a177654c953b206cf4304b40f")
				.accept(ContentType.JSON)
				.get();

		//		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();

		//to Get the city name

		String cityName = jsonPath.get("name");

		//to get the main element from json
		LinkedHashMap<String,String> mainWeather = new LinkedHashMap<String, String>();
		mainWeather= jsonPath.get("main");

		//to get the weather element from json

		List<Map<Object, Object>> weatherList = jsonPath.getList("weather");

		for (Map<Object, Object> eachValue : weatherList) {

			String mainW = (String) eachValue.get("main");

			//to print the city that has only rain and haze
			if ((mainW.contains("Rain")) || (mainW.contains("Haze"))){

				System.out.println(" City " + cityName);
				System.out.println(" Weather condition : " + mainW);
				System.out.println(" Main Weather Forecast : " + mainWeather);
				System.out.println("---------------------------");


			}

		}

	}

}
