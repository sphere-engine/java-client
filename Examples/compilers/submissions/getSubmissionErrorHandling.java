package compilers.submissions;
/**
 * Example presents error handling for getSubmission() API method
 */

import java.util.Map;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmissionErrorHandling 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				"your_access_token", 
				"compilers.sphere-engine.com");
		
		try {
			JsonObject response = client.getSubmission(2016);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Submission does not exist");
		}
	}	
}