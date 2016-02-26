import com.SphereEngine.Api.Api;
import com.SphereEngine.Api.Settings;
import com.SphereEngine.Api.Modules.Compilers;
import com.SphereEngine.Api.Modules.Problems;

public class Program 
{

	public static void main(String[] args) {
		Settings settings = new Settings()
				.setCompilersAccessToken("e7a5298eaab2666705871a0d6afaef37")
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson")
				.setProblemsAccessToken("5f0470b710157fd99a5a001955b010bb13ce7eb5")
				.setProblemsVersion("v3");
				
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		Problems p_client = api.getProblemsClient();
		
//		System.out.println(c_client.test().toString());
//		System.out.println(p_client.test().toString());
//		System.out.println(p_client.getJudges(10,0,"master").toString());
//		System.out.println(p_client.getProblems().toString());
//		System.out.println(p_client.getProblem("TEST").toString());
		System.out.println(p_client.getProblem("SE_0012").toString());
//		System.out.println(c_client.getSubmission(43337581).toString());
//		System.out.println(c_client.getSubmission("43339766", "1", "1", null, null, null).toString());
//		System.out.println(c_client.getSubmission("43339806", "1", "1", null, null, null).toString());
//		System.out.println(c_client.createSubmission("b", "11", "").toString());
	}
	
}
