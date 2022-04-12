package ch.noseryoung.plj;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    private Scanner scan = new Scanner(System.in);
    private ArrayList<Movie> movieList = new ArrayList<>();

public void createMovie(){
    movieList.add(new Movie("Titanic", "English", 3.15, "Drama", 12));
    movieList.add(new Movie("Bird Box", "German", 2.4, "Thriller", 16));
    movieList.add(new Movie("Fast & Furious 9", "English", 2.23, "Action", 13));
}

public void printMovie(){
    createMovie();
    int listingMovie = 1;
    int movieChoice = 0;

    System.out.println("What film do you want to watch? (1-3): ");
    for (int i = 0; i < movieList.size(); i++){
        IO.outPutListInBox(new String[]{listingMovie + ". " + movieList.get(i).getMovieName()}, 2);
        listingMovie++;
    }

    switch (movieChoice){
        case 1 -> System.out.println("print details of first film");
        case 2 -> System.out.println("print details of second film");
        case 3 -> System.out.println("print details of third film");
        default -> System.out.println("wrong input");
    }
}


}
