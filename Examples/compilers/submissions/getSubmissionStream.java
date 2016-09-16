package compilers.submissions;
/**
 * Example presents usage of the successful getSubmissionStream() API method  
 */

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmissionStream
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		JsonObject response = client.getSubmissionStream(2016, "output");
	}	
}
