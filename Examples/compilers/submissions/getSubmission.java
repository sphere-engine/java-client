package compilers.submissions;
/**
 * Example presents usage of the successful getSubmission() API method  
 */

import com.SphereEngine.Api.CompilersClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class getSubmission
{

    public static void main(String[] args) 
    {
        CompilersClientV3 client = new CompilersClientV3(
                "<access_token>", 
                "<endpoint>");
        
        try {
            JsonObject response = client.getSubmission(2016);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (NotFoundException e) {
            System.out.println("Submission does not exist");
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
