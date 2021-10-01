import java.util.Scanner;

public class Welcome {
	
		
	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();

		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","�lvaro", "Magalh�es");
		user1.setStyle("formal");
		welcomeServices.putUser(user1);

		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","Jo�o","House");
		user2.setStyle("casual");
		welcomeServices.putUser(user2);

		User user3 = new User ("123.456.777-01","manuela@domain.com","Dra.","Manuela","Cavalcanti");
		welcomeServices.putUser(user3);

		try (Scanner sc = new Scanner(System.in)) {
			while(true){
				String searchVariable = sc.nextLine();
				if(searchVariable.equals("0")){break;}
				if(welcomeServices.verifyCpf(searchVariable)) {
					System.out.println(welcomeServices.searchUserByCpf(searchVariable));
				}
				else if(welcomeServices.verifyEmailAdress(searchVariable)){
					System.out.println(welcomeServices.searchUserByEmailAdress(searchVariable));
				}
				else {
					System.out.println("Usu�rio n�o encontrado");
					System.out.println("invalid CPF "+searchVariable);
				}
			}
		}
	}
}
