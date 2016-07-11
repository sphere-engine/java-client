package problems;
/**
 * Example presents usage of the successful getCompilers() API method  
 */

import javax.ws.rs.NotAuthorizedException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getCompilersErrorHandling
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			JsonObject response = client.getCompilers();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
