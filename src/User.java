/**Class for objects of type Users, where values and methods for it will be contained.*/
 public class User {
	private String cpf;
	private String email;
	private String title;
	private String first_name;
	private String last_name;
	private String style;

	public User(String cpf, String email, String title, String first_name, String last_name){
		this.cpf = cpf;
		this.email = email;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public String getCpf(){return cpf;}
	public String getEmail(){return email;}
	public String getTitle(){return title;}
	public String getFirst_name(){return first_name;}
	public String getLast_name(){return last_name;}
	public String getStyle(){return style;}

	public void setCpf(String cpf){this.cpf = cpf;}
	public void setEmail(String email){this.email = email;}
	public void setTitle(String title){this.title = title;}
	public void setFirst_name(String first_name){this.first_name = first_name;}
	public void setLast_name(String last_name){this.last_name = last_name;}
	public void setStyle(String style){this.style = style;}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", title=" + title + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", Style=" + style + "]";
	}
}
