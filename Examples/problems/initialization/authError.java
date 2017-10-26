package problems.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Problems API client
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;

public class authError 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"wrong_access_token",
				"<endpoint>");
		
		try {
			client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ClientException e) {
			// client error
		} catch (ConnectionException e) {
			// connection problem
		}
	}	
}
