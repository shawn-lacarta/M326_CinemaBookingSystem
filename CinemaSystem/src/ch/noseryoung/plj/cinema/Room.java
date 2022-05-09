package ch.noseryoung.plj.cinema;

import ch.noseryoung.plj.seat.Seat;

import java.util.ArrayList;

/**
 * The room class is responsible for every room in the cinema. In
 * rooms are seats, which is created in this class.
 */
public class Room {
    private Movie movie;
    private ArrayList<Seat> seat = new ArrayList();
    private int roomNumber;
}
