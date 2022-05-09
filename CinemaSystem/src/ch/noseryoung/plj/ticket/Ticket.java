package ch.noseryoung.plj.ticket;

import ch.noseryoung.plj.IO;
import ch.noseryoung.plj.cinema.Movie;
import ch.noseryoung.plj.person.user.User;

/**
 * Responsible for the ticket in this application
 */
public class Ticket {

    private Movie movie;
    private TicketExtra ticketExtra;

    public Ticket(Movie movie, TicketExtra ticketExtra) {
        this.movie = movie;
        this.ticketExtra = ticketExtra;
    }

    public Ticket() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public TicketExtra getTicketExtra() {
        return ticketExtra;
    }

    public void setTicketExtra(TicketExtra ticketExtra) {
        this.ticketExtra = ticketExtra;
    }
}
