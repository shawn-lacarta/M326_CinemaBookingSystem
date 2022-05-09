package ch.noseryoung.plj.ticket;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.cinema.Movie;
import ch.noseryoung.plj.person.user.User;

import java.util.ArrayList;

public class TicketManager {

    IO io = new IO();

    private ArrayList<Ticket> tickets = new ArrayList<>();
    private User user;

    public TicketManager(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketManager(User user) {
        this.user = user;
        fillTickets();
    }

    public TicketManager() {
    }

    public void fillTickets() {
        tickets.add(new Ticket(new Movie("Titanic", "german", 3.5, "Romance", 1997), TicketExtra.NACHOS));
        tickets.add(new Ticket(new Movie("Once upon a Time in Hollywood", "english", 3.0, "Action", 2019), TicketExtra.NACHOS_DRINK));
        tickets.add(new Ticket(new Movie("Ice Age", "german", 2.5, "Action", 2008), TicketExtra.POPCORN));
    }

    public void chooseTicket() {
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
