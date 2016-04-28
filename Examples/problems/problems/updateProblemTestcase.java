package problems.problems;
/**
 * Example presents usage of the successful updateProblemTestcase() API method  
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemTestcase 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String problemCode = "TEST";
		Integer testcaseNumber = 0;
		String newInput = "New testcase input";
		
		JsonObject response = client.updateProblemTestcase(problemCode, testcaseNumber, newInput);
	}	
}
