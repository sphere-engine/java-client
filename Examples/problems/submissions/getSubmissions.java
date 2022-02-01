package problems.submissions;
/**
 * Example presents error handling for getSubmissions() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class getSubmissions
{

    public static void main(String[] args) 
    {
        ProblemsClientV3 client = new ProblemsClientV3(
                "<access_token>", 
                "<endpoint>");
        
        try {
            Integer[] ids = new Integer[2];
            ids[0] = 2017;
            ids[1] = 2018;

            JsonObject response = client.getSubmissions(ids);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
