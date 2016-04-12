package compilers.submissions;
/**
 * Example presents usage of the successful getSubmission() API method  
 */

import java.util.Map;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmission 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		JsonObject response = client.getSubmission(2016);
	}	
}