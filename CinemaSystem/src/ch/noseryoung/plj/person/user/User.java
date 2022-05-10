package ch.noseryoung.plj.person.user;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.person.Person;
import ch.noseryoung.plj.ticket.Ticket;
import ch.noseryoung.plj.ticket.TicketManager;

public class User extends Person {

    UserDB userDB = new UserDB();
    IO io = new IO();
    TicketManager ticketManager = new TicketManager(this);

    private String email;
    private Ticket ticket;

    public User(String firstName, String lastName, String email, String password, String username) {
        super(firstName, lastName, username, password);
        this.email = email;
    }

    public User() {
    }

    public void loginUser() {
        userDB.loginUser();
        userMenu();
    }

    public void createUser() {
        userDB.insertData();
    }

    public void deleteUser() {
        userDB.deleteData();
    }

    public void userMenu() {
        int answer = 1;

        while (answer <= 3 && answer >= 1) {
            answer = io.userMenu();

            switch (answer) {
                case 1: deleteUser();
                answer = 0;
                break;
                case 2: ticketManager.chooseTicket();
                case 3: showTickets();
            }
        }
    }

    public void showTickets() {
        io.showAllOwnTickets(ticket);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
