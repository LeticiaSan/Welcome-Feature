import java.util.Scanner;

public class Welcome {

	public static void main(String[] args) {
		Banco_de_Dados bd = new Banco_de_Dados();
		Scanner sc = new Scanner(System.in);
		bd.dicionario();
		String cpf = sc.nextLine();
		
		System.out.println(bd.boasVindas(cpf));
		
	}

}
