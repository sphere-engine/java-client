package problems.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Problems API client
 */

import com.SphereEngine.Api.ProblemsClientV3;

import javax.ws.rs.NotAuthorizedException;

public class authError 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"wrong_access_token",
				"problems.sphere-engine.com");
		
		try {
			client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
