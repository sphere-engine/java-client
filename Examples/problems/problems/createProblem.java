package problems.problems;
/**
 * Example presents usage of the successful createProblem() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class createProblem
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String code = "EXAMPLE";
		String name = "Example problem"; 
		
		try {
			JsonObject response = client.createProblem(code, name);
			// response.get("id") stores the ID of the created problem
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Masterjudge does not exist");
		} catch (BadRequestException e) {
			// aggregates four possible reasons of 400 error
			// empty problem code, empty problem name, not unique problem code, invalid problem code
			System.out.println("Bad request (empty problem code, empty problem name, not unique problem code, invalid problem code), details available in the message: " + e.getMessage());
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
