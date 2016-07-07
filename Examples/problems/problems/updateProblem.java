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
				"<access_token>", 
				"<endpoint>");
		
		String newProblemName = "New example problem name";
		
		JsonObject response = client.updateProblem("EXAMPLE", newProblemName);
	}	
}
