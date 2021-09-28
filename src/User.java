public class User {
	public String cpf;
	public String email;
	public String title;
	public String first_name;
	public String last_name;
 	public String style;

	public User(String cpf, String email, String title, String first_name, String last_name, String style){
		this.cpf = cpf;
		this.email = email;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
		this.style = style;
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", title=" + title + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", Style=" + style + "]";
	}
}
