package problems.problems;
/**
 * Example presents usage of the successful updateProblemActiveTestcases() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class updateProblemActiveTestcases
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		Integer[] activeTestcases = {1,2,3};
		
		try {
			JsonObject response = client.updateProblemActiveTestcases("EXAMPLE", activeTestcases);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the problem is forbidden");
		} catch (NotFoundException e) {
			System.out.println("Non existing problem");
		} catch (BadRequestException e) {
			System.out.println("Empty problem code");
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
