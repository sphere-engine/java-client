package problems.judges;
/**
 * Example presents usage of the successful createJudge() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class createJudge 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		String source = "<source code>";
		Integer compiler = 11; // C language
		
		try {
			JsonObject response = client.createJudge(source, compiler);
			// response.get("id") stores the ID of the created judge
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Compiler does not exist");
		} catch (BadRequestException e) {
			System.out.println("Empty source");
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
