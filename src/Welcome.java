import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Welcome {
		
	public static void main(String[] args) {
		Map<String, User> mapCpf = new HashMap<String, User>();
		Map<String, User> mapEmail = new HashMap<String, User>();
		
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		User user3 = new User(null,"gerdadir@domain.com","Dr.","Gerdadir","Cavalcanti");
		
		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user1.cpf),user1);
		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user2.cpf),user2);
		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user3.cpf),user3);
		
		mapEmail.put(user1.email,user1);
		mapEmail.put(user2.email,user2);
		mapEmail.put(user3.email,user3);
		
		WelcomeServices.welcomeServices(mapCpf, mapEmail);		
		
		try (Scanner sc = new Scanner(System.in)) {
			while(true){
				String searchVariable = sc.nextLine();
				if(WelcomeServices.verifyCpf(searchVariable)) {
					System.out.println(WelcomeServices.searchUserByCpf(WelcomeServices.turnCpfNumbersOnly(searchVariable)));
				}
				else if(WelcomeServices.verifyEmailAdress(searchVariable)) {
					System.out.println(WelcomeServices.searchUserByEmailAdress(searchVariable));
				}
				else {
					System.out.println("Usuário não encontrado");
				}
			}
		}
	}
}
