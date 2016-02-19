import com.SphereEngine.Api.Api;
import com.SphereEngine.Api.Modules.Compilers;
import com.SphereEngine.Api.Modules.Problems;

public class Program 
{

	public static void main(String[] args) {
		Api seApi = new Api("e7a5298eaab2666705871a0d6afaef37", "v3", "robson");
		Compilers c_client = seApi.getCompilersClient();
		//Problems p_client = seApi.getProblemsClient();
		
		System.out.println(c_client.getSubmission("43337581", "1", "1", null, null, null).toString());
		System.out.println(c_client.getSubmission("43339766", "1", "1", null, null, null).toString());
		System.out.println(c_client.getSubmission("43339806", "1", "1", null, null, null).toString());
		//System.out.println(c_client.createSubmission("b", "11", "").toString());
	}
	
}
