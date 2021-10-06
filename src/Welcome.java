import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.*;

public class Welcome {
	private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		WelcomeServices welcomeServices = new WelcomeServices();

		User user1 = new User("123.456.789-01","alvaro@domain.com","Sr.","Álvaro", "Magalhães");
		user1.setStyle("formal");
		welcomeServices.putUser(user1);

		User user2 = new User("123.456.779-01","joao@domain.com","Dr.","João","House");
		user2.setStyle("random");
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
					System.out.println("Usuário não encontrado");
					logr.log(Level.WARNING, "invalid cpf: " + searchVariable);
				}
			}
		}
	}
}
