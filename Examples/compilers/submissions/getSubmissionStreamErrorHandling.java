package compilers.submissions;
/**
 * Example presents error handling for getSubmissionStream() API method
 */

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmissionStreamErrorHandling 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			JsonObject response = client.getSubmissionStream(2016, "nonexistingstream");
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			// aggregates two possible reasons of 404 error
			// non existing submission or stream
			System.out.println("Non existing resource (submission, stream), details available in the message: " + e.getMessage());
		}
	}	
}
