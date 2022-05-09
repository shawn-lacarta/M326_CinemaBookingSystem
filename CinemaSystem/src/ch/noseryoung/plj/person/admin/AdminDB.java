package ch.noseryoung.plj.person.admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the connection to the database for the admin user. The
 * connection is captured here in this class.
 */
public class AdminDB {

    private Connection connection = null;
    private final String userName = "stduser";
    private final String password = "stduserpw";
    private final String URL = "jdbc:mariadb://localhost:3306/Cinema-DB";
    private final String driver = "org.mariadb.jdbc.Driver";
    Admin admin;
    Scanner sc = new Scanner(System.in);

    public AdminDB() {
    }

    /**
     * This method tests the connection of the database.
     */
    public void testConnection() {
        try {
            setConnection(DriverManager.getConnection(URL, userName, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets data from the admin database
     * @return arraylist with all admin user
     */
    public ArrayList<Admin> getData() {
        String output = "";
        ArrayList<Admin> admins = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, userName, password);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM admins");
            while (rs.next()) {
                admins.add(new Admin((String) rs.getObject(2), ((String) rs.getObject(3)),
                        ((String) rs.getObject(4)), (int) rs.getObject(5), ((String) rs.getObject(6)) ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return admins;
    }

    /**
     * Inserts data (admin) to the database.
     */
    public void insertData() {

        boolean errorOccurred = true;
        int workerId = 0;
        int generatedKey = 0;

        System.out.println("Enter your first name: ");
        String firstName = sc.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();

        while (errorOccurred) {
            try {
                System.out.println("Enter your workerId: ");
                workerId = sc.nextInt();
                sc.nextLine();
                errorOccurred = false;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Wrong Input");
                errorOccurred = true;
                sc.nextLine();
            }
        }

        System.out.println("Enter your username: ");
        String userName = sc.nextLine();

        System.out.println("Enter your password: ");
        String pwd = sc.nextLine();

        try {
            connection = DriverManager.getConnection(URL, userName, password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO admins " + "VALUES (" + generatedKey + ", '" + firstName + "' , '" + lastName + "', '" + pwd + "', '" + workerId + "', '" + userName + "')",
                    Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            admin = new Admin();

            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setPassword(pwd);
            admin.setUsername(userName);
            admin.setWorkerId(workerId);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        updateTable();

    }

    /**
     * Deletes data from the database
     */
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

            String query = "DELETE FROM admins WHERE firstname='" + firstName + "' AND lastname='" + lastName + "' AND password='" + pwd + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.execute();

            System.out.println("User deleted");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    /**
     * Checks if the user is logged in
     * @return true or false
     */
    public boolean loginUser() {
        boolean isLoggedIn = false;
        ArrayList<Admin> admins = getData();

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

                for (int i = 0; i < admins.size(); i++) {
                    if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword().equals(pwd)) {
                        isLoggedIn = true;
                        admin = new Admin(admins.get(i).getFirstName(), admins.get(i).getLastName(), admins.get(i).getPassword(), admins.get(i).getWorkerId(), admins.get(i).getUsername());

                        admin.setFirstName(admins.get(i).getFirstName());
                        admin.setLastName(admins.get(i).getLastName());
                        admin.setUsername(admins.get(i).getUsername());
                        admin.setPassword(admins.get(i).getPassword());
                        admin.setWorkerId(admins.get(i).getWorkerId());

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

    /**
     * Responsible to update the table for admins
     */
    public void updateTable() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(URL, userName, password);

            String query = "UPDATE admins SET firstname=" + admin.getFirstName() + ", lastmame=" + admin.getLastName() + ", password=" + admin.getPassword() + ", workerId=" + admin.getWorkerId() + "username=" + admin.getUsername() + " WHERE firstName='" + admin.getFirstName() + "' AND lastName='" + admin.getLastName() + "' AND password='" + admin.getPassword() + "'";
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
