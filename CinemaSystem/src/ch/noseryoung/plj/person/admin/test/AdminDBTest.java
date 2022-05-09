package ch.noseryoung.plj.person.admin.test;

import ch.noseryoung.plj.person.admin.Admin;
import ch.noseryoung.plj.person.admin.AdminDB;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminDBTest {

    AdminDB adminDB;

    @BeforeEach
    void setUp() {
        adminDB = new AdminDB();
        adminDB.testConnection();
    }

    @org.junit.jupiter.api.Test
    void testConnection() {
        assertNotNull(adminDB.getConnection());
    }

    @org.junit.jupiter.api.Test
    void getData() {
        ArrayList<Admin> admins;
        admins = adminDB.getData();
        assertNotNull(admins);
    }

    @org.junit.jupiter.api.Test
    void insertData() {
        boolean isInDB = false;
        adminDB.insertDataMock("TestUser", "TestUser", "TestPWD", 1, "test");
        ArrayList<Admin> admins;
        admins = adminDB.getData();

        for (Admin admin : admins){
            if (admin.getFirstName().toLowerCase().equals("testuser") && admin.getPassword().toLowerCase().equals("testpwd")){
                isInDB = true;
            }
        }

        assertTrue(isInDB);
    }

    @org.junit.jupiter.api.Test
    void deleteData() {
        boolean isInDB = false;
        adminDB.deleteDataMock("TestUser", "TestUser", "TestPWD");
        ArrayList<Admin> admins;
        admins = adminDB.getData();

        for (Admin admin : admins){
            if (admin.getFirstName().toLowerCase().equals("testuser") && admin.getPassword().toLowerCase().equals("testpwd")){
                isInDB = true;
            }
        }

        assertFalse(isInDB);
    }

    @org.junit.jupiter.api.Test
    void loginUser() {
        assertTrue(adminDB.loginUserMock("Enes", "Spahiu", "SomePWD"));
    }


}