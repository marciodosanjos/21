package model;

public class Login {
    private String email;
    private String id;
    
    public Login(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Login{" + "email=" + email + ", id=" + id + '}';
    }
    
    
    
}
