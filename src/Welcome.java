import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Welcome {
	public static boolean verifyEmailAdressRegex(String email) {
	    boolean isEmailValid = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	        	isEmailValid = true;
	        }
	    }
	    return isEmailValid;
	}
	
	public static boolean verifyCpfRegex(String cpf) {
	    boolean isCpfValid = false;
	    if (cpf != null && cpf.length() > 0) {
	        String expression = "[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(cpf);
	        if (matcher.matches()) {
	        	isCpfValid = true;
	        }
	    }
	    return isCpfValid;
	}
	
	public String turnCpfNumbersOnly(String cpfOnlyNum) {
		return cpfOnlyNum.replace(".","").replace("-","").replace(" ", "");
	}
		
	public String searchUserByCpf(Map<String, User> map, String inputCpf){
		User user = map.get(inputCpf);
		if(user != null) {
			return user.greetUser(inputCpf);
		}
		return "Usuário não encontrado aqui";
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
		
		Map<String, User> map = new HashMap<String, User>();
		map.put(welcome.turnCpfNumbersOnly(user1.cpf), user1);
		map.put(welcome.turnCpfNumbersOnly(user2.cpf), user2);
		map.put(user1.email, user1);
		map.put(user2.email, user2);
			
		//loop for test
		for(int i = 0; i < 1; i--){
			String searchVariable = sc.nextLine();
			if(Welcome.verifyCpfRegex(searchVariable) == true) {
				System.out.println(welcome.searchUserByCpf(map, welcome.turnCpfNumbersOnly(searchVariable)));
			}
			else if(Welcome.verifyEmailAdressRegex(searchVariable) == true) {
				System.out.println(welcome.searchUserByEmailAdress(map, searchVariable));
			}
			else {
				System.out.println("CPF inválido");
			}
		}
		sc.close();
	}
}
