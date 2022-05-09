package ch.noseryoung.plj.person.admin;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.person.Person;
import ch.noseryoung.plj.ticket.TicketBuilder;

/**
 * This class is responsible for the admin role in this program. Every
 * point that has to do something with the admin role is captured here.
 */
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

    /**
     * This method is responsible for the admin login.
     */
    public void loginAdmin() {
        adminDB.loginUser();
        adminMenu();
    }

    /**
     * This method is responsible for the creation of admin user.
     */
    public void createAdmin() {
        adminDB.insertData();
    }

    /**
     * This method is responsible for removing an admin user.
     * */
    public void deleteAdmin() {
        adminDB.deleteData();
    }

    /**
     * This method shows the menu as an admin user.
     */
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
