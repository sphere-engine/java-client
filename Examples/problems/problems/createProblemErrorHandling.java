package problems.problems;
/**
 * Example presents error handling for createProblem() API method  
 */

import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createProblemErrorHandling 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		String code = "EXAMPLE";
		String name = "Example problem"; 
		
		try {
			JsonObject response = client.createProblem(code, name);
			// response.get("id") stores the ID of the created problem
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Masterjudge does not exist");
		} catch (BadRequestException e) {
			// aggregates four possible reasons of 400 error
			// empty problem code, empty problem name, not unique problem code, invalid problem code
			System.out.println("Bad request (empty problem code, empty problem name, not unique problem code, invalid problem code), details available in the message: " + e.getMessage());
		}
	}	
}
