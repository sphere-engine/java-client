package problems.judges;
/**
 * Example presents usage of the successful getJudge() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class getJudge 
{

    public static void main(String[] args) 
    {
        ProblemsClientV3 client = new ProblemsClientV3(
                "<access_token>", 
                "<endpoint>");
        
        try {
            JsonObject response = client.getJudge(1);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (ForbiddenException e) {
            System.out.println("Access to the judge is forbidden");
        } catch (NotFoundException e) {
            System.out.println("Judge does not exist");
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
