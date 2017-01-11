package compilers.submissions;
/**
 * Example presents error handling for getSubmissions() API method
 */

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmissionsErrorHandling 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
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
