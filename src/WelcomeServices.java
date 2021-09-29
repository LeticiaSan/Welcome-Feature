import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class WelcomeServices{
	    
		public Map<String, User> mapCpf = new HashMap<String, User>();
		public Map<String, User> mapEmail = new HashMap<String, User>();
		public boolean enableSearchByEmail;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
		public int hourNow = Integer.parseInt(dtf.format(LocalDateTime.now()));
		Random random = new Random();

		public Boolean getEnableSearchByEmailValueFromProperties(String filename) {

			try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

				Properties prop = new Properties();

				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);
					System.out.println("Email search enabled: false");
					return false;
				}

				prop.load(input);

				String stringProperty = (String) prop.get("enableSearchByEmail");
				boolean booleanProperty = setStringToBoolean(stringProperty);

				if(stringProperty == null){
					System.out.println("Sorry, unable to find the property 'enableSearchByEmail' in " + filename);
				}

				System.out.println("Email search enabled: " + booleanProperty);
				return booleanProperty;
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public void inputEnableSearchByEmail(String filename){
			this.enableSearchByEmail = getEnableSearchByEmailValueFromProperties(filename);
		}

		public boolean setStringToBoolean(String s){
			if(s == null){
				return false;
			}
			return s.equals("true") || s.equals("1");
		}

		public void putUser(User user, UserConstructor constructor) {
			this.mapCpf.put(turnCpfNumbersOnly(user.cpf), user);
			this.mapEmail.put(user.email, user);
			cleanConstructor(constructor);
		}

		public void cleanConstructor(UserConstructor constructor){
			constructor.cpf = null;
			constructor.email = null;
			constructor.title = null;
			constructor.first_name = null;
			constructor.last_name = null;
			constructor.style = null;
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
				return greetUser(user) + "\nSearch made by the CPF " + inputCpf.charAt(0)+ inputCpf.charAt(1)+ inputCpf.charAt(2)+"*";
			}
			return "Usuário não encontrado \nSearch made by the CPF \nCPF: " + inputCpf.charAt(0)+ inputCpf.charAt(1)+ inputCpf.charAt(2) + "*, not found";
		}
		
		public String searchUserByEmailAdress(String inputEmail){
			User user = this.mapEmail.get(inputEmail);
			if(user != null) {
				return greetUser(user) +"\nSearch made by the Email";
			}
			return "Usuário não encontrado \nSearch made by the Email \nEmail Adress: "+ inputEmail + ", not found";
			
		}

		public String greetUser(User user){
			System.out.println("style: " + user.style);
			if (user.style != null) {
				return switch (user.style) {
					case "formal" -> greetType(user, 1);
					case "informal" -> greetType(user, 2);
					case "casual" -> greetType(user, 3);
					case "southtern" -> greetType(user, 4);
					case "random" -> greetType(user, random.nextInt(5));
					default -> greetType(user, 0);
				};
			}
			else {
				return greetType(user, 0);
			}
		}

		public String greetType(User user, int number){
			return switch (number) {
				case 1 -> formalGreetUser(user);
				case 2 -> "Hi there, " + user.title + " " + user.first_name + " " + user.last_name;
				case 3 -> "Yo, " + user.title + " " + user.first_name + " " + user.last_name;
				case 4 -> "Howdy, " + user.title + " " + user.first_name + " " + user.last_name;
				default -> "Hello, " + user.title + " " + user.first_name + " " + user.last_name;
			};
		}

		public String formalGreetUser(User user) {
			if(hourNow > 5 && hourNow < 12) {
				return "Good Morning, " + user.title + " " + user.first_name + " " + user.last_name;
			}
			if(hourNow < 18){
				return "Good Afternoon, " + user.title + " " + user.first_name + " " + user.last_name;
			}
			return "Good Evening, " + user.title + " " + user.first_name + " " + user.last_name;
		}

		public String listMap(Map<String, User> mapCpf) {
			return mapCpf.toString();
		}


}
