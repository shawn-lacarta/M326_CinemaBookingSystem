package ch.noseryoung.plj.ticket;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.cinema.Movie;
import ch.noseryoung.plj.person.user.User;

import java.util.ArrayList;

/**
 * Every logical things that is made with tickets is captured in this class
 */
public class TicketManager {

    IO io = new IO();

    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private User user;

    public TicketManager(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketManager(User user) {
        this.user = user;
    }

    public TicketManager() {
    }

    /**
     * Adding data for the program
     */
    public void fillTickets() {
        tickets.add(new Ticket(new Movie("Titanic", "german", 3.5, "Romance", 1997), TicketExtra.NACHOS));
        tickets.add(new Ticket(new Movie("Once upon a Time in Hollywood", "english", 3.0, "Action", 2019), TicketExtra.NACHOS_DRINK));
        tickets.add(new Ticket(new Movie("Ice Age", "german", 2.5, "Action", 2008), TicketExtra.POPCORN));
    }

    /**
     * User can choose a ticket here
     */
    public void chooseTicket() {
        fillTickets();
        String answer = io.showAllTickets(tickets);
        boolean hasChosenTicket = false;
        for(Ticket ticket : getTickets()) {
            if (ticket.getMovie().getMovieName().equals(answer)) {
                user.setTicket(ticket);
                hasChosenTicket = true;
            }
        }
        if (!hasChosenTicket) {
            System.out.println("There is no such ticket");
        }
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
