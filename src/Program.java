import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.SphereEngine.Api.Api;
import com.SphereEngine.Api.Settings;
import com.SphereEngine.Api.Modules.Compilers;
import com.SphereEngine.Api.Modules.Problems;

public class Program 
{
	
	public static void main(String[] args) {
		Map<String, String> env = System.getenv();
		Settings settings = new Settings()
				.setCompilersAccessToken(env.get("SE_ACCESS_TOKEN_COMPILERS"))
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson")
				.setProblemsAccessToken(env.get("SE_ACCESS_TOKEN_PROBLEMS"))
				.setProblemsVersion("v3");
				
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		Problems p_client = api.getProblemsClient();
		
		
//		System.out.println(p_client.createJudge("SRL JAVA JUDGE", 1, "", "SRL JAVA JUDGE"));
//		System.out.println(p_client.updateJudge(1158, "SRL JAVA JUDGE UPDATE", 11, "aa"));
		System.out.println(p_client.getJudge(1158));
		
//		System.out.println(c_client.test());
//		System.out.println(c_client.createSubmission("#include <stdio.h>\n int main() { printf(\"test\"); return 0; }", 1, "a"));
//		System.out.println(c_client.getSubmission(43808992, true, true, true, true, true));
//		System.out.println(p_client.createSubmission(problemCode, source));
//		System.out.println(p_client.createProblem("SRL_JAVA_TEST2", 
//				"Sphere Research Labs Java Library Test Problem", 
//				"Sphere Research Labs Java Library Test Problem", 
//				"max", 
//				false, 
//				1001));
//		System.out.println(p_client.createProblemTestcase("SRL_JAVA_TEST2", "2", "3", 1.4, 1, false));
//		System.out.println(p_client.updateProblemActiveTestcases("SRL_JAVA_TEST2", new Integer[0]));
//		System.out.println(p_client.updateProblem("SRL_JAVA_TEST2", null, null, "max", true, null, null));
//		System.out.println(p_client.updateProblemTestcase("SRL_JAVA_TEST2", 0, "{file: 1234}", "2234", 1.3, 2));
//		System.out.println(p_client.getProblemTestcaseFile("SRL_JAVA_TEST2", 0, "input"));
//		System.out.println(p_client.getProblemTestcaseFile("SRL_JAVA_TEST2", 0, "output"));
//		System.out.println(p_client.getProblemTestcaseFile("SRL_JAVA_TEST2", 0, "judge"));
//		System.out.println(p_client.createSubmission("SRL_JAVA_TEST2", "#include <stdio.h>\n int main() { printf(\"test\"); return 0;}", 11));
		
//		try {
//		System.out.println(p_client.getSubmission(146368));
//		} catch (NotFoundException e) {
//			System.out.println(e);
//		}
		
		
//		System.out.println(c_client.test().toString());
//		System.out.println(p_client.test().toString());
//		System.out.println(p_client.getJudges(10,0,"master").toString());
//		System.out.println(p_client.getProblems().toString());
//		System.out.println(p_client.getProblem("TEST").toString());
//		System.out.println(p_client.getProblem("SE_0012").toString());
//		System.out.println(p_client.getProblemTestcases("SE_0012").toString());
//		System.out.println(p_client.getProblemTestcase("SE_0012", 0).toString());
		
//		System.out.println(c_client.getSubmission(43337581).toString());
//		System.out.println(c_client.getSubmission("43339766", "1", "1", null, null, null).toString());
//		System.out.println(c_client.getSubmission("43339806", "1", "1", null, null, null).toString());
//		System.out.println(c_client.createSubmission("b", "11", "").toString());
	}
}
