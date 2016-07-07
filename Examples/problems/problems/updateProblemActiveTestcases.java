package problems.problems;
/**
 * Example presents usage of the successful updateProblemActiveTestcases() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemActiveTestcases 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		Integer[] activeTestcases = {1,2,3};
		
		JsonObject response = client.updateProblemActiveTestcases("EXAMPLE", activeTestcases);
	}	
}
