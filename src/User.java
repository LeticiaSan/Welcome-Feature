import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class User {
	public String cpf;
	public String email;
	public String title;
	public String first_name;
	public String last_name;
 	public String style;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	public int hourNow = Integer.parseInt(dtf.format(LocalDateTime.now()));
	Random random = new Random();

	public User(String cpf, String email, String title, String first_name, String last_name, String style){
		this.cpf = cpf;
		this.email = email;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
		this.style = style;
	}

	public String greetUser(){
		System.out.println("style: " + this.style);
		return switch (this.style){
			case "formal" -> greetType(1);
			case "informal" -> greetType(2);
			case "casual" -> greetType(3);
			case "southtern" -> greetType(4);
			case "random" -> greetType(random.nextInt(5));
			default -> greetType(0);
		};
	}

	public String greetType(int number){
		return switch (number) {
			case 1 -> formalGreetUser();
			case 2 -> "Hi there, " + this.title + " " + this.first_name + " " + this.last_name;
			case 3 -> "Yo, " + this.title + " " + this.first_name + " " + this.last_name;
			case 4 -> "Howdy, " + this.title + " " + this.first_name + " " + this.last_name;
			default -> "Hello, " + this.title + " " + this.first_name + " " + this.last_name;
		};
	}

	public String formalGreetUser() {
		if(hourNow > 5 && hourNow < 12) {
			return "Good Morning, " + this.title + " " + this.first_name + " " + this.last_name;
		}
		if(hourNow < 18){
			return "Good Afternoon, " + this.title + " " + this.first_name + " " + this.last_name;
		}
		return "Good Evening, " + this.title + " " + this.first_name + " " + this.last_name;
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", title=" + title + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", Style=" + style + "]";
	}
}
