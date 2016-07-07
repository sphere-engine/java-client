package problems.problems;
/**
 * Example presents error handling for getProblemTestcases() API method   
 */

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getProblemTestcasesErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "NONEXISTING_CODE";
		
		try {
			JsonObject response = client.getProblemTestcases(problemCode);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			System.out.println("Problem does not exist");
		}
	}	
}
