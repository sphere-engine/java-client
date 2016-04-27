package problems;
/**
 * Example presents usage of the successful getCompilers() API method  
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getCompilers 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		JsonObject response = client.getCompilers();
	}	
}