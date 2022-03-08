package Demo;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse 
{
	public static void main(String[] args) 
	{
	
		// 1. Print No of courses returned by API
	
		JsonPath js = new JsonPath(Payload.coursefile());
		int count= js.getInt("courses.size()");
		System.out.println(count);
	
		// 2. Print Purchase Amount
		
		int amount= js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		// 3. Print Title of the first course
		
		String title=js.getString("courses[0].title");
		System.out.println(title);
	
		// 4. Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++)
		{
		String titles=js.getString("courses["+i+"].title");	
		System.out.println(titles);
//		int price=js.getInt("courses["+i+"].price");
//		System.out.println(price);

		System.out.println(js.get("courses["+i+"].price").toString());					//sysout always expect string argument
		
		
		// 5. Print no of copies sold by RPA Course
		
/*		
		int copiesRPA = js.getInt("courses[2].copies");
		System.out.println(copiesRPA);
*/		
		//but if change the array of RPA so using the following method
		
		System.out.println("Print no of copies sold by RPA Course");
		
		for(i=0;i<count;i++)
		{
			String alltitles=js.getString("courses["+i+"].title");
			if(alltitles.equalsIgnoreCase("RPA"))
			{
				//copies sold 
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}	
		}		
		}
	}
}
