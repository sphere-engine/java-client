package problems.initialization;
/**
 * Example presents usage of the successful initialization of 
 * Sphere Engine Problems API client
 */

import java.util.Map;

import com.SphereEngine.Api.ProblemsClientV3;

public class initialization 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
	}	
}