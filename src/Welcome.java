import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Welcome {
		
	public static void main(String[] args) {
		Map<String, User> mapCpf = new HashMap<String, User>();
		Map<String, User> mapEmail = new HashMap<String, User>();
		
		WelcomeServices welcomeServices = new WelcomeServices(mapCpf, mapEmail);
		
		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		User user3 = new User("123.456.777-01","gerdadir@domain.com","Dr.","Gerdadir","Cavalcanti");
		
		welcomeServices.putCpf(user1);
		welcomeServices.putCpf(user2);
		welcomeServices.putCpf(user3);
		
		welcomeServices.putEmail(user1);
		welcomeServices.putEmail(user2);
		welcomeServices.putEmail(user3);
		
		System.out.println(welcomeServices.listarMap(welcomeServices.mapCpf)); 
		
//		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user1.cpf),user1);
//		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user2.cpf),user2);
//		mapCpf.put(WelcomeServices.turnCpfNumbersOnly(user3.cpf),user3);
//		
//		mapEmail.put(user1.email,user1);
//		mapEmail.put(user2.email,user2);
//		mapEmail.put(user3.email,user3);
		
		//WelcomeServices.welcomeServices(mapCpf, mapEmail);		
		
		try (Scanner sc = new Scanner(System.in)) {
			while(true){
				String searchVariable = sc.nextLine();
				if(welcomeServices.verifyCpf(searchVariable)) {
					System.out.println(welcomeServices.searchUserByCpf(welcomeServices.turnCpfNumbersOnly(searchVariable)));
				}
				else if(welcomeServices.verifyEmailAdress(searchVariable)) {
					System.out.println(welcomeServices.searchUserByEmailAdress(searchVariable));
				}
				else {
					System.out.println("Usuário não encontrado");
				}
			}
		}
	}
}
