public class UserConstructor {
    public String cpf;
    public String email;
    public String title;
    public String first_name;
    public String last_name;
    public String style;

    public User construct(){
        return new User(cpf, email, title, first_name, last_name, style);
    }
    public UserConstructor insertCpf(String cpf){
        this.cpf = cpf;
        return this;
    }
    public UserConstructor insertEmail(String email){
        this.email = email;
        return this;
    }
    public UserConstructor insertTitle(String title){
        this.title = title;
        return this;
    }
    public UserConstructor insertFirst_name(String first_name){
        this.first_name = first_name;
        return this;
    }
    public UserConstructor insertLast_name(String last_name){
        this.last_name = last_name;
        return this;
    }
    public UserConstructor insertStyle(String style){
        this.style = style;
        return this;
    }
}
