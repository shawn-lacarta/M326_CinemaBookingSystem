package ch.noseryoung.plj;

import java.util.ArrayList;

public class Manager {

    private ArrayList<Movie> movieList = new ArrayList<>();

public void createMovie(){
    movieList.add(new Movie("titanic", "english", 3.15, "drama", 12));
    movieList.add(new Movie("bird box", "german", 2.4, "thriller", 16));
    movieList.add(new Movie("fast & furious 9", "english", 2.23, "action", 13));
}
}
