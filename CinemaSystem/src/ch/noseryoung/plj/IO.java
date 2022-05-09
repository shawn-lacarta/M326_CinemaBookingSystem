package ch.noseryoung.plj;

import ch.noseryoung.plj.cinema.Movie;
import ch.noseryoung.plj.ticket.Ticket;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

    Scanner sc = new Scanner(System.in);

    public int startProgram() {
        int answer = 0;
        boolean errorOccured = true;

        while (errorOccured){
            try {
                drawBox(40, "1. Login User ");
                drawBox(40, "2. Login Admin  ");
                drawBox(40, "3. Sign Up");
                drawBox(40, "4. Exit ");
                answer = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }
        return answer;
    }

    public int adminMenu() {
        int answer = 0;
        boolean errorOccured = true;

        while (errorOccured){
            try {
                drawBox(40, "1. Delete Admin ");
                drawBox(40, "2. Create Admin ");
                drawBox(40, "3. Create Ticket");
                drawBox(40, "4. Exit ");
                answer = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }
        return answer;
    }

    public int userMenu() {
        int answer = 0;
        boolean errorOccured = true;

        while (errorOccured){
            try {
                drawBox(40, "1. Delete User");
                drawBox(40, "2. Buy Ticket ");
                drawBox(40, "3. Show Tickets ");
                drawBox(40, "4. Exit ");
                answer = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }
        return answer;
    }

    public String showAllTickets(ArrayList<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            drawBox(80, ticket.getMovie().getMovieName() + " / " + ticket.getTicketExtra().toString());
        }

        System.out.println("Which one do you want to buy: ");
        String answer = sc.nextLine();

        return answer;
    }
    public void showAllOwnTickets(Ticket ticket) {
        if (ticket != null) {
            drawBox(80, ticket.getMovie().getMovieName() + " / " + ticket.getTicketExtra().toString());
        } else {
            System.out.println("You currently have no ticket");
        }
    }


    public int cinemaMenu() {
        int answer = 0;
        boolean errorOccured = true;

        while (errorOccured){
            try {
                drawBox(40, "1. Buy ticket");
                drawBox(40, "2. Exit");
                answer = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }
        return answer;
    }

    public Movie createMovieDialog() {

        int year = 0;
        boolean errorOccured = true;
        double duration = 0;

        System.out.println("Name: ");
        String movieName = sc.nextLine();

        System.out.println("Language: ");
        String language = sc.nextLine();

        while (errorOccured){
            try {
                System.out.println("Duration: ");
                duration = sc.nextDouble();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }

        System.out.println("Genre: ");
        String genre = sc.nextLine();

        errorOccured = true;

        while (errorOccured){
            try {
                System.out.println("Year: ");
                year = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }

        return new Movie(movieName, language, duration, genre, year);
    }

    public int ticketExtraDialog() {
        int answer = 0;
        boolean errorOccured = true;

        while (errorOccured){
            try {
                drawBox(40, "1. Popcorn");
                drawBox(40, "2. Nachos ");
                drawBox(40, "3. Popcorn and Drink");
                drawBox(40, "4. Nachos and Drink ");
                drawBox(40, "5. Nothing");
                answer = sc.nextInt();
                sc.nextLine();
                errorOccured = false;
            }catch (InputMismatchException e){
                System.out.println("Wrong Input");
                sc.nextLine();
            }
        }
        return answer;
    }

    public void drawBox(int length, String singleWord){

        if (singleWord.length() > length){
            length = singleWord.length();
        }

        if (length % 2 != 0){
            length--;
        }

        System.out.print("\u2554");

        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }

        System.out.print("\u2557\n");
        System.out.print("\u2551");

        for (int j = 0; j < (length - singleWord.length()) / 2; j++) {
            System.out.print(" ");
        }

        System.out.print("" + singleWord);

        for (int j = 0; j < (length - singleWord.length()) / 2; j++) {
            System.out.print(" ");
        }

        System.out.print("\u2551\n");
        System.out.print("\u255A");

        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }
        System.out.print("\u255D\n");
    }
}
