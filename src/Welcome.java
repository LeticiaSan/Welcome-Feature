import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Welcome {
	public String searchUserByCpf(Map<String, User> map, String inputCpf){
		User user = map.get(inputCpf);
		if(user != null) {
			return user.greetUser(inputCpf);
		}
		return "Usuário não encontrado";
	}
	
	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		Scanner sc = new Scanner(System.in);
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		
		Map<String, User> map = new HashMap<String, User>();
		map.put(user1.cpf, user1);
		map.put(user2.cpf, user2);
		
		//loop para teste
		for(int i = 0; i < 1; i --) {
			String inputCpf = sc.nextLine();
			System.out.println(welcome.searchUserByCpf(map, inputCpf));
		}					
	}

}
