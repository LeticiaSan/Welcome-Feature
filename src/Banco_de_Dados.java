import java.util.HashMap;
import java.util.Map;

public class Banco_de_Dados {
	
	private String cpf;
	private String email;
	private String title;
	private String first_name;
	private String last_name;
	
	public void dicionario() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cpf", "123.456.789-01");
		this.cpf = map.get("cpf");
		
		map.put("email","alvaro@domain.com");
		this.email = map.get("email");
		
		map.put("title", "Sr.");
		this.title = map.get("title");
		
		map.put("first_name", "Álvaro");
		this.first_name = map.get("first_name");
		
		map.put("last_name","Magalhães");
		this.last_name = map.get("last_name");		
	}
	
	public String boasVindas(String cpf){
		if (cpf.equals(this.cpf)) {
			return "Olá, "+ this.title + " " + this.first_name;
		}
		else {
			return "Usuário não encontrado";
		}
	}
	
	public String getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public String getTitle() {
		return title;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
}
