package ch.noseryoung.plj.person.user.test;

import ch.noseryoung.plj.person.user.User;
import ch.noseryoung.plj.person.user.UserDB;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserDBTest {

    UserDB userDB;

    @BeforeEach
    void setUp() {
        userDB = new UserDB();
        userDB.testConnection();
    }

    @org.junit.jupiter.api.Test
    void testConnection() {
        assertNotNull(userDB.getConnection());
    }

    @org.junit.jupiter.api.Test
    void getData() {
        ArrayList<User> users;
        users = userDB.getData();
        assertNotNull(users);
    }

    @org.junit.jupiter.api.Test
    void insertData() {
        boolean isInDB = false;
        userDB.insertDataMock("TestUser", "TestUser", "TestPWD", "test", "test");
        ArrayList<User> users;
        users = userDB.getData();

        for (User user : users){
            if (user.getFirstName().toLowerCase().equals("testuser") && user.getPassword().toLowerCase().equals("testpwd")){
                isInDB = true;
            }
        }

        assertTrue(isInDB);
    }

    @org.junit.jupiter.api.Test
    void deleteData() {
        boolean isInDB = false;
        userDB.deleteDataMock("TestUser", "TestUser", "TestPWD");
        ArrayList<User> users;
        users = userDB.getData();

        for (User user : users){
            if (user.getFirstName().toLowerCase().equals("testuser") && user.getPassword().toLowerCase().equals("testpwd")){
                isInDB = true;
            }
        }

        assertFalse(isInDB);
    }

    @org.junit.jupiter.api.Test
    void loginUser() {
        assertTrue(userDB.loginUserMock("Enes", "Spahiu", "SomePWD"));
    }


}