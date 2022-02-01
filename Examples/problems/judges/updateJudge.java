package problems.judges;
/**
 * Example presents usage of the successful updateJudge() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class updateJudge
{

    public static void main(String[] args) 
    {
        ProblemsClientV3 client = new ProblemsClientV3(
                "<access_token>", 
                "<endpoint>");
        
        String source = "<source code>";
        Integer compiler = 11; // C language
        
        try {
            JsonObject response = client.updateJudge(1, source, compiler);
        } catch (NotAuthorizedException e) {
            System.out.println("Invalid access token");
        } catch (ForbiddenException e) {
            System.out.println("Access to the judge is forbidden");
        } catch (NotFoundException e) {
            // aggregates two possible reasons of 404 error
            // non existing judge or compiler
            System.out.println("Non existing resource (judge, compiler), details available in the message: " + e.getMessage());
        } catch (BadRequestException e) {
            System.out.println("Empty source");
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
    }	
}
