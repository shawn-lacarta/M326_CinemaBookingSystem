package ch.noseryoung.plj.ticket;

import ch.noseryoung.plj.IO;

public class TicketBuilder {

    TicketManager tm = new TicketManager();
    IO io = new IO();

    public void createTicket() {

        tm.getTickets().add(new Ticket(io.createMovieDialog(), ticketExtra()));
    }

    private TicketExtra ticketExtra() {
        int answer = io.ticketExtraDialog();

        switch (answer) {
            case 1: return TicketExtra.POPCORN;
            case 2: return TicketExtra.NACHOS;
            case 3: return TicketExtra.POPCORN_DRINK;
            case 4: return TicketExtra.NACHOS_DRINK;
            default: return TicketExtra.NOTHING;
        }
    }

}
