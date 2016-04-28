package problems.submissions;
/**
 * Example presents usage of the successful createSubmission() API method
*/

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createSubmission 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String problemCode = "TEST";
		String source = "int main() { return 0; }";
		Integer compiler = 11; // C language
		
		JsonObject response = client.createSubmission(problemCode, source, compiler);
		// response.get("id") stores the ID of the created submission
	}	
}
