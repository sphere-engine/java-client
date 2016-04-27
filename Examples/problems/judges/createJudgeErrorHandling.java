package problems.judges;
/**
 * Example presents error handling for createJudge() API method  
 */

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class createJudgeErrorHandling 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"your_access_token", 
				"problems.sphere-engine.com");
		
		String source = "int main() { return 0; }";
		Integer nonexistingCompiler = 9999;
		
		try {
			JsonObject response = client.createJudge(source, nonexistingCompiler);
			// response.get("id") stores the ID of the created judge
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (NotFoundException e) {
			System.out.println("Compiler does not exist");
		} catch (BadRequestException e) {
			System.out.println("Empty source");
		}
	}	
}
