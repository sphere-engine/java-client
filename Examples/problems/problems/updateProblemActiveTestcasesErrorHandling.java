package problems.problems;
/**
 * Example presents error handling for updateProblemActiveTestcases() API method  
 */

import java.util.Map;

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
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
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
