import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class User {
	public String cpf;
	public String email;
	public String title;
	public String first_name;
	public String last_name;
 	public String style;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	public String hourNow = dtf.format(LocalDateTime.now());
	public int hourNowInt = Integer.parseInt(hourNow);

	public User(String cpf, String email, String title, String first_name, String last_name, String style){
		this.cpf = cpf;
		this.email = email;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
		this.style = style;
	}

	public String greetUser(String searchVariable){
		if(this.style.equals("formal")){
			return formalGreetUser(searchVariable);
		}
		return informalGreetUser(searchVariable);
	}
	public String formalGreetUser(String searchVariable) {
		if(hourNowInt > 5 && hourNowInt < 12) {
			return "Good Morning, " + this.title + " " + this.first_name + " " + this.last_name;
		}
		if(hourNowInt < 18){
			return "Good Afternoon, " + this.title + " " + this.first_name + " " + this.last_name;
		}
		return "Good Evening, " + this.title + " " + this.first_name + " " + this.last_name;
	}
	public String informalGreetUser(String searchVariable) {
		return "Hello, " + this.title + " " + this.first_name + " " + this.last_name;
	}
	
	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", title=" + title + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", Style=" + style + "]";
	}
}
