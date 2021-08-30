import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Welcome {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		
		Map<String, User> map = new HashMap<String, User>();
		map.put(user1.cpf, user1);
		
		String cpf = sc.nextLine();
		
		for (String key : map.keySet()) {
			if(cpf.equals(key)) {
				System.out.println(user1.boasVindas(key));
			}
		}
		
	}

}
