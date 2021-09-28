import java.util.Scanner;

public class Welcome {
		
	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();
		welcomeServices.inputEnableSearchByEmail("welcome.properties");
		UserConstructor constructor = new UserConstructor();
		User user1 = constructor.insertCpf("123.456.789-01").insertEmail("alvaro@domain.com").insertTitle("Sr.").insertFirst_name("Álvaro").insertLast_name("Magalhães").insertStyle("formal").construct();
		User user2 = constructor.insertCpf("123.456.779-01").insertEmail("joao@domain.com").insertTitle("Dr.").insertFirst_name("João").insertLast_name("House").insertStyle("informal").construct();
		User user3 = constructor.insertCpf("123.456.777-01").insertEmail("manuela@domain.com").insertTitle("Dra.").insertFirst_name("Manuela").insertLast_name("Cavalcanti").construct();

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
