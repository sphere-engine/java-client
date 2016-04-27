package problems.problems;
/**
 * Example presents usage of the successful createProblem() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createProblem 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String code = "EXAMPLE";
		String name = "Example problem";
		
		JsonObject response = client.createProblem(code, name);
		// response.get("id") stores the ID of the created problem
	}	
}
