import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Welcome {
	public String searchUserByCpf(Map<String, User> map, String input_cpf){
		if(input_cpf != null) {
			for (Entry<String, User> user : map.entrySet()) {
				if(input_cpf.equals(user.getKey())) {
					return user.getValue().greetUser(input_cpf);
				}
			}
			return "Usu�rio n�o encontrado";
		}
		return "Usu�rio n�o encontrado";
	}
	
	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		Scanner sc = new Scanner(System.in);
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","�lvaro","Magalh�es");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","Jo�o","House");
		
		String input_cpf = sc.nextLine();
		
		Map<String, User> map = new HashMap<String, User>();
		map.put(user1.cpf, user1);
		map.put(user2.cpf, user2);
		System.out.println(welcome.searchUserByCpf(map, input_cpf));
		
	}

}
