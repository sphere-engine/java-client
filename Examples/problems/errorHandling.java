package problems;
/**
 * Example presents complete error handling schema for calling API methods of
 * Sphere Engine Problems API client
*/

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import com.SphereEngine.Api.ProblemsClientV3;

public class errorHandling
{

	public static void main(String[] args)
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		// complete error handling
		try {
		    // any API method usage
		    // client.methodName(parameters..)
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the resource is forbidden");
		} catch (NotFoundException e) {
			System.out.println("Resource does not exist");
			// more details about missing resource are provided in $e->getMessage()
	        // possible missing resources depend on called API method
		} catch (BadRequestException e) {
			System.out.println("Bad request");
			// more details about missing resource are provided in $e->getMessage()
	    	// possible reasons depend on called API method
		} catch (WebApplicationException e) {
			System.out.println("API connection problem");
		} finally {
			// handle other exceptions (connection or network errors etc.)
		}
	}
}
