package compilers;
/**
 * Example presents usage of the successful getCompilers() API method  
 */

import javax.ws.rs.NotAuthorizedException;

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getCompilersErrorHandling
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			JsonObject response = client.getCompilers();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
