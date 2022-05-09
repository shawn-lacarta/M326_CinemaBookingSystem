package ch.noseryoung.plj.person.user;

import ch.noseryoung.plj.person.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserDB {

    private Connection connection = null;
    private final String userName = "stduser";
    private final String password = "stduserpw";
    private final String URL = "jdbc:mariadb://localhost:3306/Cinema-DB";
    private final String driver = "org.mariadb.jdbc.Driver";
    User user;
    Scanner sc = new Scanner(System.in);

    public UserDB() {
    }

    public void testConnection() {
        try {
            setConnection(DriverManager.getConnection(URL, userName, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getData() {
        String output = "";
        ArrayList<User> users = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, userName, password);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User((String) rs.getObject(2), ((String) rs.getObject(3)),
                        ((String) rs.getObject(4)), ((String) rs.getObject(5)), ((String) rs.getObject(6))));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    public void insertData() {

        boolean errorOccurred = true;
        int workerId = 0;
        int generatedKey = 0;

        System.out.println("Enter your first name: ");
        String firstName = sc.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();

        System.out.println("Enter your username: ");
        String username = sc.nextLine();

        System.out.println("Enter your password: ");
        String pwd = sc.nextLine();

        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        try {
            connection = DriverManager.getConnection(URL, userName, password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users " + "VALUES (" + generatedKey + ", '" + firstName + "' , '" + lastName + "', '" + email + "', '" + pwd + "', '" + username + "')",
                    Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            System.out.println("Successfully Signed in");
            System.out.println("Welcome to our Cinema");

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        updateTable();

    }

    public void deleteData() {
        try {

            System.out.println("Type in first name of Account: (Look out for Capital letters)");
            String firstName = sc.nextLine();

            System.out.println("Type in last name of Account: (Look out for Capital letters)");
            String lastName = sc.nextLine();

            System.out.println("Type in password of Account: (Look out for Capital letters)");
            String pwd = sc.nextLine();

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(URL, userName, password);

            String query = "DELETE FROM users WHERE firstname='" + firstName + "' AND lastname='" + lastName + "' AND password='" + pwd + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.execute();

            System.out.println("User deleted");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public boolean loginUser() {
        boolean isLoggedIn = false;
        ArrayList<User> users;

        users = getData();

        try {
            connection = DriverManager.getConnection(URL, userName, password);
            while (!isLoggedIn) {
                System.out.println("Type in username of Account or (s) to stop: (Look out for Capital letters)");
                String username = sc.nextLine();

                if (username.toLowerCase().equals("s")) {
                    break;
                }

                System.out.println("Type in password of Account or (s) to stop: (Look out for Capital letters)");
                String pwd = sc.nextLine();

                if (pwd.toLowerCase().equals("s")) {
                    break;
                }

                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(pwd)) {
                        isLoggedIn = true;
                        user = new User(users.get(i).getFirstName(), users.get(i).getLastName(), users.get(i).getUsername(), users.get(i).getPassword(), users.get(i).getEmail());

                        user.setFirstName(users.get(i).getFirstName());
                        user.setLastName(users.get(i).getLastName());
                        user.setUsername(users.get(i).getUsername());
                        user.setPassword(users.get(i).getPassword());

                        break;
                    } else {
                        isLoggedIn = false;
                    }
                }
                if (isLoggedIn) {
                    System.out.println("Successfully logged in");
                } else {
                    System.out.println("There is no such user, try again");
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return isLoggedIn;
    }

    public void updateTable() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(URL, userName, password);

            String query = "UPDATE users SET firstname=" + user.getFirstName() + ", lastmame=" + user.getLastName() + ", password=" + user.getPassword() + ", email=" + user.getEmail() + "username=" + user.getUsername() + " WHERE firstName='" + user.getFirstName() + "' AND lastName='" + user.getLastName() + "' AND password='" + user.getPassword() + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
