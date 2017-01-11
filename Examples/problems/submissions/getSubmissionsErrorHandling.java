package problems.submissions;
/**
 * Example presents error handling for getSubmissions() API method  
 */

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getSubmissionsErrorHandling
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			Integer[] ids = new Integer[2];
			ids[0] = 2017;
			ids[1] = 2018;

			JsonObject response = client.getSubmissions(ids);
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
