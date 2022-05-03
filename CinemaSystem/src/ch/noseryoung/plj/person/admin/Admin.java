package ch.noseryoung.plj.person.admin;

import ch.noseryoung.plj.person.Person;

public class Admin extends Person {
    private int workerId;

    public Admin(String firstName, String lastName, String username, String password, int workerId) {
        super(firstName, lastName, username, password);
        this.workerId = workerId;
    }

    public Admin() {
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}
