package problems.problems;
/**
 * Example presents error handling for updateProblemActiveTestcases() API method  
 */

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class updateProblemActiveTestcasesErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String problemCode = "NONEXISTING_CODE";
		Integer[] activeTestcases = {1,2,3};
		
		try {
			JsonObject response = client.updateProblemActiveTestcases(problemCode, activeTestcases);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			System.out.println("Non existing problem");
		} catch (BadRequestException e) {
			System.out.println("Empty problem code");
		}
	}	
}
