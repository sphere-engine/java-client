package compilers.submissions;
/**
 * Example presents usage of the successful getSubmissionStream() API method  
 */

import com.SphereEngine.Api.CompilersClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class getSubmissionStream
{

    public static void main(String[] args) 
    {
        CompilersClientV3 client = new CompilersClientV3(
                "<access_token>", 
                "<endpoint>");
        
        try {
            String response = client.getSubmissionStream(2016, "output");
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (NotFoundException e) {
            // aggregates two possible reasons of 404 error
            // non existing submission or stream
            System.out.println("Non existing resource (submission, stream), details available in the message: " + e.getMessage());
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
