package compilers.initialization;
/**
 * Example presents authorization error handling for 
 * Sphere Engine Compilers API client
 */

import com.SphereEngine.Api.CompilersClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;

public class authError 
{

    public static void main(String[] args) 
    {
        CompilersClientV3 client = new CompilersClientV3(
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
