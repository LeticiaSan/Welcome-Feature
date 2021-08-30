public class User {
	public String cpf;
	public String email;
	public String title;
	public String first_name;
	public String last_name;
	
	public User(String cpf, String email, String title, String first_name, String last_name){
		this.cpf = cpf;
		this.email = email;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public String boasVindas(String cpf) {
		if(this.cpf.equals(cpf)) {
			return "Olá, "+ this.title + " " + this.first_name;
		}
		else
		{
			return "Usuário não encontrado";
		}
	}
}
