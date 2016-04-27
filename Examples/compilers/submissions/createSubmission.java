package compilers.submissions;
/**
 * Example presents usage of the successful createSubmission() API method
*/

import java.util.Map;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class createSubmission 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				"your_access_token", 
		
		String source = "int main() { return 0; }";
		Integer compiler = 11; // C language
		String input = "2016";
		
		JsonObject response = client.createSubmission(source, compiler, input);
		// response.get("id") stores the ID of the created submission
	}	
}
