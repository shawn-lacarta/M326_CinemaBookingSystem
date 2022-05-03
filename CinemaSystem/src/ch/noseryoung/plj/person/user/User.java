package ch.noseryoung.plj.person.user;

import ch.noseryoung.plj.person.Person;

public class User extends Person {
    private String email;

    public User(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password);
        this.email = email;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
