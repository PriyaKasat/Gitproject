package Demo;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class sumValidation 
{
	@Test
	public void sumofvalidate()
	{
		JsonPath js = new JsonPath(Payload.coursefile());
		int count= js.getInt("courses.size()");
		int sum = 0;
															//Jcommander mvn repository is used
		for(int i=0;i<count;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum= sum+amount;
		}
		
		System.out.println(sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);		
	}
}
