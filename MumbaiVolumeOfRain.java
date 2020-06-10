package exercise2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MumbaiVolumeOfRain {

	@Test
	public void getVolumeofRain() throws ParseException {



		//	https://api.openweathermap.org/data/2.5/onecall?lat=19.01&lon=72.85&start=1591362395&end=1591621595&exclude=daily&appid=c397236a177654c953b206cf4304b40f


		RestAssured.baseURI= "http://api.openweathermap.org/data/2.5/onecall";


		//coverting Date Time to epoch time format

		String startDate = "2020-06-08 09:18:03.000";
		String endDate = "2020-06-10 09:18:03.000";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date sDate = df.parse(startDate);
		Date eDate = df.parse(endDate);
		long epochStartDate = sDate.getTime();
		long epochEndDate = eDate.getTime();

		System.out.println(epochStartDate);


		Map<String, String> parameterMap = new HashMap<String, String>();

		parameterMap.put("lat", "19.0");
		parameterMap.put("lon", "72.85");
		parameterMap.put("exclude", "daily");
		parameterMap.put("start", String.valueOf(epochStartDate));
		parameterMap.put("end", String.valueOf(epochEndDate));
		parameterMap.put("appid", "c397236a177654c953b206cf4304b40f");


		Response response = RestAssured
				.given()
				.log().all() 
				.params(parameterMap)
				.accept(ContentType.JSON)
				.get();

		if(response.getStatusCode()==200) {
			System.out.println(" Valid Response Returned");
		}

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		List<Object> hourList = jsonPath.getList("hourly");
		int size = hourList.size();
		float rainfall, totalVolume =0.0f;


		for (int i =0 ; i<size ; i++) {

			if (jsonPath.get("hourly["+i+"].rain")!=null)
			{

				rainfall = jsonPath.get("hourly["+i+"].rain.1h");
				totalVolume =totalVolume+ rainfall;
			}
		}

		System.out.println("Total Volume :" + totalVolume + "mm");
	}

}
