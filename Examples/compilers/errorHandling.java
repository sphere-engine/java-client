package compilers;
/**
 * Example presents complete error handling schema for calling API methods of
 * Sphere Engine Compilers API client
*/

import com.SphereEngine.Api.CompilersClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConflictException;
import com.SphereEngine.Api.Exception.ConnectionException;

public class errorHandling
{

    public static void main(String[] args)
    {
        CompilersClientV3 client = new CompilersClientV3(
                "<access_token>", 
                "<endpoint>");
        
        // complete error handling
        try {
            // any API method usage
            // client.methodName(parameters..)
            // example:
            client.test();
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
        } catch (ConflictException e) {
            System.out.println("Conflict exception (code 409)");
        } catch (ClientException e) {
            System.out.println("Client error");
            // others uncaught client exception
            // more details are provided in $e->getMessage()
        } catch (ConnectionException e) {
            System.out.println("API connection problem");
        }
    }
}
