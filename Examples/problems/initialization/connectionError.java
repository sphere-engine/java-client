package problems.initialization;
/**
 * Example presents connection error handling for 
 * Sphere Engine Problems API client
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;

import javax.ws.rs.WebApplicationException;

public class connectionError 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		try {
			client.test();
		} catch (WebApplicationException e) {
			System.out.println("API connection problem");
		} finally {
			// handle other exceptions (connection or network errors etc.)
		}
	}	
}