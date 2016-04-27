package problems.problems;
/**
 * Example presents usage of the successful updateProblem() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblem 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String newProblemName = "New example problem name";
		
		JsonObject response = client.updateProblem("EXAMPLE", newProblemName);
	}	
}
