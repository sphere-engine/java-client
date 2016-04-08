package problems.submissions;
/**
 * Example presents error handling for getSubmission() API method  
 */

import java.util.Map;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getSubmissionErrorHandling
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		try {
			JsonObject response = client.getSubmission(2016);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Submission does not exist");
		}
	}	
}
