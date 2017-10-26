package problems.initialization;
/**
 * Example presents connection error handling for 
 * Sphere Engine Problems API client
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.SphereEngine.Api.Exception.ClientException;

public class connectionError 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		try {
			client.test();
		} catch (ConnectionException e) {
			System.out.println("API connection problem");
		} catch (ClientException e) {
			// client error
		}
	}	
}
