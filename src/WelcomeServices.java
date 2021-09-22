import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WelcomeServices{
	    
		public Map<String, User> mapCpf = new HashMap<String, User>();
		public Map<String, User> mapEmail = new HashMap<String, User>();
		public boolean enableSearchByEmail;

		public Boolean getEnableSearchByEmailValueFromProperties(String filename) {

			try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

				Properties prop = new Properties();

				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);
					return null;
				}

				prop.load(input);
				return setStringToBoolean((String) prop.get("enableSearchByEmail"));
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public void inputEnableSearchByEmail(String filename){
			this.enableSearchByEmail = getEnableSearchByEmailValueFromProperties(filename);
		}

		public static boolean setStringToBoolean(String s){
			return s.equals("true") || s.equals("1");
		}

		public void putUser(User user) {
			this.mapCpf.put(turnCpfNumbersOnly(user.cpf), user);
			this.mapEmail.put(user.email, user);
		}
				
		public boolean verifyByRegex(String input, String regex) {
		    boolean isInputValid = false;
		    if (input != null && !input.isEmpty()) {
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
			User user = this.mapCpf.get(turnCpfNumbersOnly(inputCpf));
			if(user != null) {
				return user.greetUser(inputCpf);
			}
			return "Usuário não encontrado";
		}
		
		public String searchUserByEmailAdress(String inputEmail){
			User user = this.mapEmail.get(inputEmail);
			if(user != null && enableSearchByEmail) {
				return user.greetUser(inputEmail);
			}
			return "Usuário não encontrado";
			
		}
		
		public String listMap(Map<String, User> mapCpf) {
			return mapCpf.toString();
		}

}
