package compilers.submissions;
/**
 * Example presents usage of the successful createSubmission() API method
*/
import javax.ws.rs.NotAuthorizedException;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class createSubmissionErrorHandling 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String source = "<source code>";
		Integer compiler = 11; // C language
		String input = "2016";
		
		try {
			JsonObject response = client.createSubmission(source, compiler, input);
			// response.get("id") stores the ID of the created submission
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
