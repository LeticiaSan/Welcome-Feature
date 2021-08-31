import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Welcome {
	public String searchUserByCpf(Map<String, User> map, String input_cpf){
		if(map.containsKey(input_cpf)) {
			return map.get(input_cpf).greetUser(input_cpf);
		}
		return "Usuário não encontrado";
	}
	
	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		Scanner sc = new Scanner(System.in);
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		
		String input_cpf = sc.nextLine();
		
		Map<String, User> map = new HashMap<String, User>();
		map.put(user1.cpf, user1);
		map.put(user2.cpf, user2);
				
		System.out.println(welcome.searchUserByCpf(map, input_cpf));
		
	}

}
