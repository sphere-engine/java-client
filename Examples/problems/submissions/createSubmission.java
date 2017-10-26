package problems.submissions;
/**
 * Example presents error handling for createSubmission() API method
*/

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class createSubmission
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "TEST";
		String source = "<source code>";
		Integer compiler = 11; // C language
		
		try {
			JsonObject response = client.createSubmission(problemCode, source, compiler);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			// aggregates three possible reasons of 404 error
			// non existing problem, compiler or user
			System.out.println("Non existing resource (problem, compiler or user), details available in the message: " + e.getMessage());
		} catch (BadRequestException e) {
			System.out.println("Empty source code");
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
