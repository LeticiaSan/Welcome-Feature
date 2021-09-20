import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeServices{
	    
		public Map<String, User> mapCpf = new HashMap<String, User>();
		public Map<String, User> mapEmail = new HashMap<String, User>();
		
		public WelcomeServices(Map<String, User> mapCpf, Map<String, User> mapEmail){
			this.mapCpf = mapCpf;
			this.mapEmail = mapEmail;
		}
		
		public void putCpf(User user) {
			mapCpf.put(user.cpf, user);
		}
		
		public String listarMap(Map<String, User> mapCpf) {
			return mapCpf.toString();
		}
		
		public void putEmail(User user) {
			mapEmail.put(user.email, user);
		}
		
		public boolean verifyByRegex(String input, String regex) {
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
		
		public boolean verifyEmailAdress(String email) {
			return verifyByRegex(email,"^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$");
		}

		public boolean verifyCpf(String cpf) {
			return verifyByRegex(cpf,"[0-9]{3}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[-]?[0-9]{2}");
		}
				
		public String turnCpfNumbersOnly(String cpfOnlyNum) {
			if(cpfOnlyNum != null)
			{
				return cpfOnlyNum.replace(".","").replace("-","").replace(" ", "");
			}
			return "";
		}
		
		public String searchUserByCpf(String inputCpf){
			User user = mapCpf.get(inputCpf);
			if(user != null) {
				return user.greetUser(inputCpf);
			}
			return "NULOUsuário não encontrado";
		}
		
		public String searchUserByEmailAdress(String inputEmail){
			User user = mapEmail.get(inputEmail);
			if(user != null) {
				return user.greetUser(inputEmail);
			}
			return "Usuário não encontrado";
			
		}
		
		
}
