package problems.problems;
/**
 * Example presents usage of the successful createProblemTestcase() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class createProblemTestcase
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
		Integer judgeId = 1;
		
		try {
			JsonObject response = client.createProblemTestcase(code, input, output, timelimit, judgeId);
			// response.get("number") stores the number of created testcase
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			// aggregates two possible reasons of 400 error
			// non existing problem and judge
			System.out.println("Non existing resource (problem or judge), details available in the message: " + e.getMessage());
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
