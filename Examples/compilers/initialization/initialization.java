package compilers.initialization;
/**
 * Example presents usage of the successful initialization of 
 * Sphere Engine Compilers API client
 */

import java.util.Map;

import com.SphereEngine.Api.CompilersClientV3;

public class initialization 
{

	public static void main(String[] args) 
	{
		Map<String, String> env = System.getenv();
		CompilersClientV3 client = new CompilersClientV3(
				"your_access_token", 
				"compilers.sphere-engine.com");
	}	
}
