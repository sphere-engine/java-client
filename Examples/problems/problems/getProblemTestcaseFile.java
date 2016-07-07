package problems.problems;
/**
 * Example presents usage of the successful getProblemTestcaseFile() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getProblemTestcaseFile 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "TEST";
		Integer testcaseNumber = 0;
		String file = "input";
		
		String response = client.getProblemTestcaseFile(problemCode, testcaseNumber, file);
	}	
}
