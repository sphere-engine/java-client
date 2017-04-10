package problems.judges;
/**
 * Example presents usage of the successful createJudge() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createJudge 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String source = "<source code>";
		Integer compiler = 11; // C language
		
		JsonObject response = client.createJudge(source, compiler);
		// response.get("id") stores the ID of the created judge
	}	
}
