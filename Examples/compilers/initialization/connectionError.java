package compilers.initialization;
/**
 * Example presents connection error handling for 
 * Sphere Engine Compilers API client
 */

import javax.ws.rs.WebApplicationException;

import com.SphereEngine.Api.CompilersClientV3;

public class connectionError 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			client.test();
		} catch (WebApplicationException e) {
			System.out.println("API connection problem");
		} finally {
			// handle other exceptions (connection or network errors etc.)
		}
	}	
}
