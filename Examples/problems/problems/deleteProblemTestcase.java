package problems.problems;
/**
 * Example presents usage of the successful deleteProblemTestcase() API method  
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class deleteProblemTestcase 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		String problemCode = "EXAMPLE";
		Integer testcaseNumber = 0;
		
		JsonObject response = client.deleteProblemTestcase(problemCode, testcaseNumber);
	}	
}
