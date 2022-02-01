package problems.problems;
/**
 * Example presents usage of the successful updateProblem() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class updateProblem
{

    public static void main(String[] args) 
    {
        ProblemsClientV3 client = new ProblemsClientV3(
                "<access_token>", 
                "<endpoint>");
        
        String newProblemName = "New example problem name";
        
        try {
            JsonObject response = client.updateProblem("EXAMPLE", newProblemName);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (ForbiddenException e) {
            System.out.println("Access to the problem is forbidden");
        } catch (NotFoundException e) {
            // aggregates two possible reasons of 404 error
            // non existing problem or masterjudge
            System.out.println("Non existing resource (problem, masterjudge), details available in the message: " + e.getMessage());
        } catch (BadRequestException e) {
            // aggregates two possible reasons of 400 error
            // empty problem code, empty problem name
            System.out.println("Bad request (empty problem code, empty problem name), details available in the message: " + e.getMessage());
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
