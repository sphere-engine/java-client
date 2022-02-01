package problems.problems;
/**
 * Example presents usage of the successful getProblemTestcase() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class getProblemTestcase
{

    public static void main(String[] args) 
    {
        ProblemsClientV3 client = new ProblemsClientV3(
                "<access_token>", 
                "<endpoint>");
        
        String problemCode = "TEST";
        Integer testcaseNumber = 0;
        
        try {
            JsonObject response = client.getProblemTestcase(problemCode, testcaseNumber);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (ForbiddenException e) {
            System.out.println("Access to the problem is forbidden");
        } catch (NotFoundException e) {
            // aggregates two possible reasons of 404 error
            // non existing problem or testcase
            System.out.println("Non existing resource (problem, testcase), details available in the message: " + e.getMessage());
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
