package problems.problems;
/**
 * Example presents error handling for updateProblem() API method  
 */

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "NONEXISTING_CODE";
		String newProblemName = "New example problem name";
		
		try {
			JsonObject response = client.updateProblem(problemCode, newProblemName);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			// aggregates two possible reasons of 404 error
			// non existing problem or masterjudge
			System.out.println("Non existing resource (problem, masterjudge), details available in the message: " + e.getMessage());
		} catch (BadRequestException e) {
			// aggregates two possible reasons of 400 error
			// empty problem code, empty problem name
			System.out.println("Bad request (empty problem code, empty problem name), details available in the message: " + e.getMessage());
		}
	}	
}
