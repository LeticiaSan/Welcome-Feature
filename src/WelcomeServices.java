import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeServices {
	    
		public static  Map<String, User> mapCpf = new HashMap<String, User>();
		public static Map<String, User> mapEmail = new HashMap<String, User>();
		
//		public WelcomeServices(Map<String, User> mapCpf, Map<String, User> mapEmail) {
//			this.mapCpf = mapCpf;
//			this.mapEmail = mapEmail;
//		}
				
		public static boolean verifyByRegex(String input, String regex) {
		    boolean isInputValid = false;
		    if (input != null && input.isEmpty() == false) {
		        String expression = regex;
		        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
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
			return "Usuário não encontrado2";
		}
		
		public String searchUserByEmailAdress(String inputEmail){
			User user = mapEmail.get(inputEmail);
			if(user != null) {
				return user.greetUser(inputEmail);
			}
			return "Usuário não encontrado3";
			
		}
		
		public static void population(){
			WelcomeServices welcomeServices = new WelcomeServices();
			
			User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
			User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
			User user3 = new User(null,"nulo@domain.com","Dr.","nulo","nulo");
			
			mapCpf.put(welcomeServices.turnCpfNumbersOnly(user1.cpf),user1);
			mapCpf.put(welcomeServices.turnCpfNumbersOnly(user2.cpf),user2);
			mapCpf.put(welcomeServices.turnCpfNumbersOnly(user3.cpf),user3);
			
			mapEmail.put(user1.email,user1);
			mapEmail.put(user2.email,user2);
			mapEmail.put(user3.email,user3);
		}
}
