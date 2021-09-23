import java.util.Scanner;

public class Welcome {
		
	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();
		welcomeServices.inputEnableSearchByEmail("welcome.properties");

		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro","Magalhães");
		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		User user3 = new User("123.456.777-01","manuela@domain.com","Dra.","Manuela","Cavalcanti");

		welcomeServices.putUser(user1);
		welcomeServices.putUser(user2);
		welcomeServices.putUser(user3);

		try (Scanner sc = new Scanner(System.in)) {
			while(true){
				String searchVariable = sc.nextLine();
				if(welcomeServices.verifyCpf(searchVariable)) {
					System.out.println(welcomeServices.searchUserByCpf(searchVariable));
				}
				else if(welcomeServices.enableSearchByEmail){
					if(welcomeServices.verifyEmailAdress(searchVariable)){
						System.out.println(welcomeServices.searchUserByEmailAdress(searchVariable));
					}
					else {
						System.out.println("Usuário não encontrado");
						System.out.println("invalid CPF "+searchVariable);
					}
				}
				else {
					System.out.println("Usuário não encontrado");
					System.out.println("invalid CPF "+searchVariable);
				}
			}
		}
	}
}
