package ch.noseryoung.plj.person.admin;

import ch.noseryoung.plj.person.admin.Admin;
import ch.noseryoung.plj.person.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    public void testConnection() {
        try {
            setConnection(DriverManager.getConnection(URL, userName, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    }

    public void insertDataMock(String firstName, String lastName, String pwd, int workerId, String username) {

        int generatedKey = 0;

        try {
            connection = DriverManager.getConnection(URL, userName, password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO admins " + "VALUES (" + generatedKey + ", '" + firstName + "' , '" + lastName + "', '" + pwd + "', '" + workerId + "', '" + username + "')",
                    Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            admin = new Admin(firstName, lastName, pwd, workerId,username);

            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setPassword(pwd);
            admin.setWorkerId(workerId);
            admin.setUsername(username);

        } catch (SQLException e){
            System.out.println("Something went wrong");
        }

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

            String query = "DELETE FROM admins WHERE firstname='" + firstName + "' AND lastname='" + lastName + "' AND password='" + pwd + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.execute();

            System.out.println("Admin deleted");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public void deleteDataMock(String firstName, String lastName, String pwd) {
        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(URL, userName, password);

            String query = "DELETE FROM admins WHERE firstname='" + firstName + "' AND lastname='" + lastName + "' AND password='" + pwd + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.execute();

            System.out.println("Admin deleted");

        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

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
                    System.out.println("There is no such admin, try again");
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return isLoggedIn;
    }

    public boolean loginUserMock(String firstName, String lastName, String pwd) {
        boolean isLoggedIn = false;
        ArrayList<Admin> users;

        users = getData();

        try{
            connection = DriverManager.getConnection(URL, userName, password);
            while (!isLoggedIn) {

                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getFirstName().equals(firstName) && users.get(i).getLastName().equals(lastName) && users.get(i).getPassword().equals(pwd)) {
                        isLoggedIn = true;
                        admin = new Admin(users.get(i).getFirstName(), users.get(i).getLastName(), users.get(i).getPassword(), users.get(i).getWorkerId(),users.get(i).getUsername());

                        admin.setFirstName(users.get(i).getFirstName());
                        admin.setLastName(users.get(i).getLastName());
                        admin.setWorkerId(users.get(i).getWorkerId());
                        admin.setUsername(users.get(i).getUsername());
                        admin.setPassword(users.get(i).getPassword());

                        break;
                    } else {
                        isLoggedIn = false;
                    }
                }
                if (isLoggedIn){
                    System.out.println("Successfully logged in");
                }else {
                    System.out.println("There is no such admin, try again");
                }
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
        return isLoggedIn;
    }

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
