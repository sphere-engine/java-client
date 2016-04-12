package compilers;
/**
 * Example presents complete error handling schema for calling API methods of 
 * Sphere Engine Compilers API client
*/

import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.CompilersClientV3;

public class errorHandling 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		// complete error handling
		try {
		    // any API method usage
		    // client.methodName(parameters..)
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ForbiddenException e) {
			System.out.println("Access to the resource is forbideen");
		} catch (NotFoundException e) {
			System.out.println("Resource does not exist");
			// more details about missing resource are provided in $e->getMessage() 
	        // possible missing resources depend on called API method
		} catch (BadRequestException e) {
			System.out.println("Bad request");
			// more details about missing resource are provided in $e->getMessage()
	    	// possible reasons depend on called API method
		} finally {
			// handle unexpected API connection errors
			// and handle other exceptions (connection or network errors etc.)
		}
	}	
}
