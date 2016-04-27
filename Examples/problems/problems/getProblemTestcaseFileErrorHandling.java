package problems.problems;
/**
 * Example presents error handling for getProblemTestcaseFile() API method   
 */

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getProblemTestcaseFileErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String problemCode = "TEST";
		Integer testcaseNumber = 0;
		String nonexistingFile = "nonexistingFile";
		
		try {
			String response = client.getProblemTestcaseFile(problemCode, testcaseNumber, nonexistingFile);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			// aggregates three possible reasons of 404 error
			// non existing problem, testcase or file
			System.out.println("Non existing resource (problem, testcase or file), details available in the message: " + e.getMessage());
		}
	}	
}
