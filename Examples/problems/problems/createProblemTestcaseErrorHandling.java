package problems.problems;
/**
 * Example presents error handling for createProblemTestcase() API method  
 */

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createProblemTestcaseErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String code = "EXAMPLE";
		String input = "model input";
		String output = "model output";
		Double timelimit = 5.0;
		Integer nonexistingJudge = 9999;
		
		try {
			JsonObject response = client.createProblemTestcase(code, input, output, timelimit, nonexistingJudge);
			// response.get("number") stores the number of created testcase
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			// aggregates two possible reasons of 400 error
			// non existing problem and judge
			System.out.println("Non existing resource (problem or judge), details available in the message: " + e.getMessage());
		}
	}	
}
