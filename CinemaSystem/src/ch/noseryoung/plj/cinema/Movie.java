package ch.noseryoung.plj.cinema;

public class Movie {

    String movieName;
    String language;
    double duration;
    String genre;
    int age;

    public Movie(String movieName, String language, double duration, String genre, int age) {
        this.movieName = movieName;
        this.language = language;
        this.duration = duration;
        this.genre = genre;
        this.age = age;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
