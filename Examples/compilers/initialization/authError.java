package compilers.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Compilers API client
 */

import com.SphereEngine.Api.CompilersClientV3;

import javax.ws.rs.NotAuthorizedException;

public class authError 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"wrong_access_token", 
				"compilers.sphere-engine.com");
		
		try {
			client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}
