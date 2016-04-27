package problems.judges;
/**
 * Example presents error handling for getJudge() API method   
 */

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getJudgeErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		Integer nonexistingJudgeId = 999999;
		
		try {
			JsonObject response = client.getJudge(nonexistingJudgeId);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the judge is forbidden");
		} catch (NotFoundException e) {
			System.out.println("Judge does not exist");
		}
	}	
}
