package problems.initialization;
/**
 * Example presents usage of the successful initialization of 
 * Sphere Engine Problems API client
 */

import com.SphereEngine.Api.ProblemsClientV3;

public class initialization 
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"<access_token>", 
				"<endpoint>");
	}	
}
