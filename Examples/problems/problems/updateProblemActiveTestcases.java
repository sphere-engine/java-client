package problems.problems;
/**
 * Example presents usage of the successful updateProblemActiveTestcases() API method  
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemActiveTestcases 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		Integer[] activeTestcases = {1,2,3};
		
		JsonObject response = client.updateProblemActiveTestcases("EXAMPLE", activeTestcases);
	}	
}
