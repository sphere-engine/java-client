package compilers.submissions;
/**
 * Example presents usage of the successful getSubmissions() API method  
 */

import com.SphereEngine.Api.CompilersClientV3;
import com.google.gson.JsonObject;

public class getSubmissions 
{

	public static void main(String[] args) 
	{
		CompilersClientV3 client = new CompilersClientV3(
				"<access_token>", 
				"<endpoint>");
		
		Integer[] ids = new Integer[2];
		ids[0] = 2017;
		ids[1] = 2018;

		JsonObject response = client.getSubmissions(ids);
	}	
}
