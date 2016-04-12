package problems.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Problems API client
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;

import javax.ws.rs.NotAuthorizedException;

public class authError 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		try {
			client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}