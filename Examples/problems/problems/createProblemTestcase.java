package problems.problems;
/**
 * Example presents usage of the successful createProblemTestcase() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createProblemTestcase 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String code = "EXAMPLE";
		String input = "model input";
		String output = "model output";
		Double timelimit = 5.0;
		Integer judgeId = 1;
		
		JsonObject response = client.createProblemTestcase(code, input, output, timelimit, judgeId);
		// response.get("number") stores the number of created testcase
	}	
}
