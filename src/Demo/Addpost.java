package Demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;
public class Addpost {

	public static void main(String[] args) 
	{
		
		// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//add the place
		
		
		
/*		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())								//import the file package
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.headers("Server","Apache/2.4.18 (Ubuntu)");
*/
		
			String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(Payload.AddPlace())								//import the file package
			.when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
			.headers("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
			System.out.println(response);						// full json body in response
		
			JsonPath js = new JsonPath(response);		// for parsing Json
			String placeid=js.getString("place_id");
		
			System.out.println(placeid);
			
			
		//update the place
			
		/*	
			given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeid+"\",\r\n"									//placeid variable is used at the place of placeid value
					+ "\"address\":\"shrilanka\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("/maps/api/place/update/json")
			.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
			
		*/	
			String newaddress="south Africa";
			
			given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeid+"\",\r\n"									//placeid variable is used at the place of placeid value
					+ "\"address\":\""+newaddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("/maps/api/place/update/json")
			.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
			
			//get place response
			
/*			
	String getplaceresponse=given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id", placeid)
			.when().get("/maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getplaceresponse);	
	String actualaddress= js1.getString("address");
	System.out.println(actualaddress);			
	Assert.assertEquals(actualaddress, newaddress);		
			
	//Cucumber junit & TestNG		
*/
			String getplaceresponse=given().log().all().queryParam("key", "qaclick123")
					.queryParam("place_id", placeid)
					.when().get("/maps/api/place/get/json")
					.then().assertThat().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js1= ReusableMethods.rawToJson(getplaceresponse);
			String actualaddress= js1.getString("address");
			System.out.println(actualaddress);			
			Assert.assertEquals(actualaddress, newaddress);		
						
			
	}
}
