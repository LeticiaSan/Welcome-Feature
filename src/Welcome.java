import java.util.Scanner;

public class Welcome {
	
		
	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();
		welcomeServices.inputEnableSearchByEmail("welcome.properties");
		UserConstructor constructor = new UserConstructor();

		User user1 = constructor.insertCpf("123.456.789-01").insertEmail("alvaro@domain.com").insertTitle("Sr.").insertFirst_name("Álvaro").insertLast_name("Magalhães").insertStyle("formal").construct();
		System.out.println(user1.toString());
		welcomeServices.putUser(user1, constructor);

		User user2 = constructor.insertCpf("123.456.779-01").insertEmail("joao@domain.com").insertTitle("Dr.").insertFirst_name("João").insertLast_name("House").insertStyle("casual").construct();
		System.out.println(user2.toString());
		welcomeServices.putUser(user2, constructor);

		User user3 = constructor.insertCpf("123.456.777-01").insertEmail("manuela@domain.com").insertTitle("Dra.").insertFirst_name("Manuela").insertLast_name("Cavalcanti").construct();
		System.out.println(user3.toString());
		welcomeServices.putUser(user3, constructor);

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
