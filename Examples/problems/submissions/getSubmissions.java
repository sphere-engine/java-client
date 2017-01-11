package problems.submissions;
/**
 * Example presents usage of the successful getSubmissions() API method  
 */

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class getSubmissions 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
		
		Integer[] ids = new Integer[2];
		ids[0] = 2017;
		ids[1] = 2018;

		JsonObject response = client.getSubmissions(ids);
	}
}
