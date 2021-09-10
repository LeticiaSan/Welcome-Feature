import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Welcome {
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
		return Welcome.verifyByRegex(email,"^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$");
	}

	public static boolean verifyCpf(String cpf) {
		return Welcome.verifyByRegex(cpf,"[0-9]{3}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[-]?[0-9]{2}");
	}
	
	public String turnCpfNumbersOnly(String cpfOnlyNum) {
		if(cpfOnlyNum != null)
		{
			return cpfOnlyNum.replace(".","").replace("-","").replace(" ", "");
		}
		return "";
	}
	
	//So, the user has to always pass the map as a parameter. 
	//Please refactor in a way that the user doesn't need to pass the map, only the CPF, for better simplicity.
	public String searchUserByCpf(Map<String, User> map, String inputCpf){
		User user = map.get(inputCpf);
		if(user != null) {
			return user.greetUser(inputCpf);
		}
		return "Usuário não encontrado";
	}
	
	public String searchUserByEmailAdress(Map<String, User> map, String inputEmail){
		User user = map.get(inputEmail);
		if(user != null) {
			return user.greetUser(inputEmail);
		}
		return "Usuário não encontrado";
		
	}

	
	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		Scanner sc = new Scanner(System.in);
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		User user3 = new User(null,"nulo@domain.com","Dr.","nulo","nulo");

		Map<String, User> mapCpf = new HashMap<String, User>();
		mapCpf.put(welcome.turnCpfNumbersOnly(user1.cpf), user1);
		mapCpf.put(welcome.turnCpfNumbersOnly(user2.cpf), user2);
		mapCpf.put(welcome.turnCpfNumbersOnly(user3.cpf), user3);
		
		Map<String, User> mapEmail = new HashMap<String, User>();
		mapEmail.put(user1.email, user1);
		mapEmail.put(user2.email, user2);
		mapEmail.put(user3.email, user3);
					
		while(true){
			String searchVariable = sc.nextLine();
			if(Welcome.verifyCpf(searchVariable)) {
				System.out.println(welcome.searchUserByCpf(mapCpf, welcome.turnCpfNumbersOnly(searchVariable)));
			}
			else if(Welcome.verifyEmailAdress(searchVariable)) {
				System.out.println(welcome.searchUserByEmailAdress(mapEmail, searchVariable));
			}
			else {
				System.out.println("Usuário não encontrado");
			}
		}
		//sc.close();
	}
}
