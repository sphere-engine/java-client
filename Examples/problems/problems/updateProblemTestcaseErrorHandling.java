package problems.problems;
/**
 * Example presents error handling for updateProblemTestcase() API method  
 */

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemTestcaseErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "TEST";
		Integer testcaseNumber = 0;
		Integer newNonexistingJudge = 9999;
		
		try {
			JsonObject response = client.updateProblemTestcase(problemCode, testcaseNumber, null, null, null, newNonexistingJudge);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			// aggregates three possible reasons of 404 error
			// non existing problem, testcase or judge
			System.out.println("Non existing resource (problem, testcase or judge), details available in the message: " + e.getMessage());
		}
	}	
}
