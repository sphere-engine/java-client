package compilers.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Compilers API client
 */

import java.util.Map;

import com.SphereEngine.Api.CompilersClientV3;

import javax.ws.rs.NotAuthorizedException;

public class authError 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		try {
			client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		}
	}	
}