import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeServices{
	    
		public static Map<String, User> mapCpf = new HashMap<String, User>();
		public static Map<String, User> mapEmail = new HashMap<String, User>();
		
		public static void welcomeServices(Map<String, User> cpf, Map<String, User> email){
			mapCpf = cpf;
			mapEmail = email;
		}
					
		public static boolean verifyByRegex(String input, String regex) {
		    boolean isInputValid = false;
		    if (input != null && input.isEmpty() == false) {
		        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		        Matcher matcher = pattern.matcher(input);
		        if (matcher.matches()) {
		        	isInputValid = true;
		        }
		    }
		    return isInputValid;
		}
		
		public static boolean verifyEmailAdress(String email) {
			return WelcomeServices.verifyByRegex(email,"^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$");
		}

		public static boolean verifyCpf(String cpf) {
			return WelcomeServices.verifyByRegex(cpf,"[0-9]{3}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[-]?[0-9]{2}");
		}
				
		public static String turnCpfNumbersOnly(String cpfOnlyNum) {
			if(cpfOnlyNum != null)
			{
				return cpfOnlyNum.replace(".","").replace("-","").replace(" ", "");
			}
			return "";
		}
		
		public static String searchUserByCpf(String inputCpf){
			User user = mapCpf.get(inputCpf);
			if(user != null) {
				return user.greetUser(inputCpf);
			}
			return "Usuário não encontrado";
		}
		
		public static String searchUserByEmailAdress(String inputEmail){
			User user = mapEmail.get(inputEmail);
			if(user != null) {
				return user.greetUser(inputEmail);
			}
			return "Usuário não encontrado";
			
		}
		
}
