//import java.util.Arrays;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.*;

/**Class containing methods aimed at the user's welcome greeting*/
public class WelcomeServices{
		private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		public Map<String, User> mapCpf = new HashMap<>();
		public Map<String, User> mapEmail = new HashMap<>();
		public Map<String, String> mapGreetStyle = new HashMap<>();
		WelcomeProperties welcomeProperties = new WelcomeProperties();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
		public int hourNow = Integer.parseInt(dtf.format(LocalDateTime.now()));
		Random random = new Random();

		/** Method for register a File with the properties*/
		public void putFileProperties(){
			welcomeProperties.inputPropertiesFile("welcome.properties");
		}

		/** Method for register a User in mapCpf and mapEmail
		 * @param user - user to be registered*/
		public void putUser(User user) {
			this.mapCpf.put(turnCpfNumbersOnly(user.getCpf()), user);
			this.mapEmail.put(user.getEmail(), user);
		}

		/** Method for register a User in mapCpf and mapEmail*/
		public void putGreetStyle(){
			String[] type = new String[5];
			type[0] = "Hello, ";
			type[1] = formalGreetUser();
			type[2] = "Hi there, ";
			type[3] = "Yo, ";
			type[4] = "Howdy, ";
			this.mapGreetStyle.put("default",type[0]);
			this.mapGreetStyle.put("formal",type[1]);
			this.mapGreetStyle.put("informal",type[2]);
			this.mapGreetStyle.put("casual",type[3]);
			this.mapGreetStyle.put("southern",type[4]);
			this.mapGreetStyle.put("random", type[random.nextInt(5)] );
		}

		/** Method for check the validation of an input, by its regex
		 * @param input - input you want to be verified
		 * @param regex - validation rule
		 * @return boolean - returns true if validation is accepted, returns false if not accepted*/
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

		/** Method for check the validation of an email
		 * @param email - email for validation
		 * @return boolean - returns true if the email is valid, returns false if not valid*/
		public boolean verifyEmailAdress(String email) {
			putFileProperties();
			if(welcomeProperties.getEnableSearchByEmail()){
				return verifyByRegex(email,"^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$");
			}
			return false;
		}

		/** Method for check the validation of a cpf
		 * @param cpf - cpf for validation
		 * @return boolean - returns true if the cpf is valid, returns false if not valid*/
		public boolean verifyCpf(String cpf) {
			return verifyByRegex(cpf,"[0-9]{3}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[-]?[0-9]{2}");
		}

		/** Method for turn a cpf with just numbers
		 * @param cpfOnlyNum - cpf for transform in only numbers
		 * @return String - returns cpf only with numbers*/
		public String turnCpfNumbersOnly(String cpfOnlyNum) {
			if(cpfOnlyNum != null)
			{
				return cpfOnlyNum.replace(".","").replace("-","").replace(" ", "");
			}
			return "";
		}

		/** Method for search a user with it respective cpf
		 * @param inputCpf - cpf for user search
		 * @return String - returns the greeting to the respective user to the search cpf*/
		public String searchUserByCpf(String inputCpf){
			User user = this.mapCpf.get(turnCpfNumbersOnly(inputCpf));
			if(user != null) {
				logr.log(Level.INFO, "search made by cpf " + inputCpf.charAt(0) + inputCpf.charAt(1) + inputCpf.charAt(2) + "*");
				return greetUser(user);
			}
			logr.log(Level.WARNING, "cpf: "+  inputCpf.charAt(0)+ inputCpf.charAt(1)+ inputCpf.charAt(2) +  "*, not found.");
			return "Usuário não encontrado";
		}

		/** Method for search a user with it respective email
		 * @param inputEmail - email for user search
		 * @return String - returns the greeting to the respective user to the search email*/
		public String searchUserByEmailAdress(String inputEmail){
			User user = this.mapEmail.get(inputEmail);
			if(user != null) {
				logr.log(Level.INFO, "search made by email " + showOnlyEmailDomain(inputEmail));
				return greetUser(user);
			}
			logr.log(Level.WARNING,inputEmail+" not found.");
			return "Usuário não encontrado";
			
		}

		/** Method for greet the user according to their style
		 * @param user - user you want to greet
		 * @return String - returns the greeting corresponding to the user's style*/
		public String greetUser(User user){
			putGreetStyle();
			putFileProperties();
			String userStyle = user.getStyle();
			if (userStyle != null && welcomeProperties.getEnableCustomUserStyles()) {
				return mapGreetStyle.get(userStyle) + user.getTitle() + " " + user.getFirst_name() + " " + user.getLast_name();
			}
			else {
				return mapGreetStyle.get("default") +  user.getTitle() + " " + user.getFirst_name() + " " + user.getLast_name();
			}
		}

		/** Method for greet in formal style, according to the time
		 * @return String - returns the greeting according to the time*/
		public String formalGreetUser() {
			if(hourNow > 5 && hourNow < 12) {
				return "Good Morning, ";
			}
			if(hourNow < 18){
				return "Good Afternoon, ";
			}
			return "Good Evening, ";
		}

		public String showOnlyEmailDomain(String email){
			int emailLength = email.length();
			int domainStartPosition = 0;
			String emailDomain = "*";

			for (int i = 0; i < emailLength; i++) {
				if (email.charAt(i) == '@') {
					domainStartPosition = i;
				}
				if(domainStartPosition != 0){
					emailDomain = emailDomain + email.charAt(i);
				}
			}
			return emailDomain;
		}

}
