package problems.submissions;
/**
 * Example presents error handling for createSubmission() API method
*/

import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createSubmissionErrorHandling
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		String problemCode = "TEST";
		String source = "int main() { return 0; }";
		Integer nonexistingCompiler = 99999;
		
		try {
			JsonObject response = client.createSubmission(problemCode, source, nonexistingCompiler);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			// aggregates three possible reasons of 404 error
			// non existing problem, compiler or user
			System.out.println("Non existing resource (problem, compiler or user), details available in the message: " + e.getMessage());
		} catch (BadRequestException e) {
			System.out.println("Empty source code");
		}
	}	
}
