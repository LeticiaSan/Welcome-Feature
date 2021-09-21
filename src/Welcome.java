import java.util.Scanner;

public class Welcome {
		
	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();
		String enableSearchByEmail = "";
		welcomeServices.inputEnableEmail(enableSearchByEmail);

		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		User user3 = new User("123.456.777-01","manuela@domain.com","Dra.","Manuela","Cavalcanti");
		
		welcomeServices.put(user1);
		welcomeServices.put(user2);
		welcomeServices.put(user3);

		try (Scanner sc = new Scanner(System.in)) {
			while(true){
				String searchVariable = sc.nextLine();
				if(welcomeServices.verifyCpf(searchVariable)) {
					System.out.println(welcomeServices.searchUserByCpf(searchVariable));
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
