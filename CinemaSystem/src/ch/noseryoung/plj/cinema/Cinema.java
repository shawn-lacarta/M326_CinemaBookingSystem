package ch.noseryoung.plj.cinema;

import ch.noseryoung.plj.IO;

import java.sql.Array;
import java.util.ArrayList;

public class Cinema {

    IO io = new IO();

    ArrayList<Room> rooms = new ArrayList<>();

    public void cinemaMenu() {
        int answer = 1;
        while (answer <= 2 && answer >= 1) {
            answer = io.cinemaMenu();

            switch (answer) {
                case 1:
            }
        }
    }

}
