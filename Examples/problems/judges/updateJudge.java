package problems.judges;
/**
 * Example presents usage of the successful updateJudge() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateJudge 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String source = "<source code>";
		Integer compiler = 11; // C language
		
		JsonObject response = client.updateJudge(1, source, compiler);
	}	
}
