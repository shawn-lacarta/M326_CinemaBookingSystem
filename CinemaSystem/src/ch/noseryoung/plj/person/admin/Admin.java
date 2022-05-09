package ch.noseryoung.plj.person.admin;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.person.Person;
import ch.noseryoung.plj.ticket.TicketBuilder;

public class Admin extends Person {

    AdminDB adminDB = new AdminDB();
    IO io = new IO();
    TicketBuilder ticketBuilder = new TicketBuilder();

    private int workerId;

    public Admin(String firstName, String lastName, String password, int workerId, String username) {
        super(firstName, lastName, username, password);
        this.workerId = workerId;
    }

    public Admin() {
    }

    public void loginAdmin() {
        adminDB.loginUser();
        adminMenu();
    }

    public void createAdmin() {
        adminDB.insertData();
    }

    public void deleteAdmin() {
        adminDB.deleteData();
    }

    public void adminMenu() {
        int answer = 1;

        while (answer <= 3 && answer >= 1) {
            answer = io.adminMenu();

            switch (answer) {
                case 1 -> deleteAdmin();
                case 2 -> createAdmin();
                case 3 -> ticketBuilder.createTicket();
            }
        }
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}
