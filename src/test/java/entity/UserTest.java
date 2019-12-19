package entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import entity.User;
import org.junit.jupiter.api.Test;





public class UserTest {
    static String password = "Billy";
    static String username = "Shazam";

    static String xyz = "Billy2";
    static String abc = "Shazam2";
    static String fl = "Flash";

    @Test
    public void testUser() {
        User newuser = new User(username,password);
        assertTrue(newuser.getUsername().equals(username));
        assertTrue(newuser.getPassword().equals(password));

        User newuser2 = new User(15,abc,xyz,100);
        assertTrue(newuser2.getUsername().equals(abc));
        assertTrue(newuser2.getPassword().equals(xyz));

    }

    @Test
    public void testGetUser() {
        User newuser = new User(username,password);
        assertTrue(newuser.getUsername().equals(username));
    }

    @Test
    public void testSetUser() {
        User newuser = new User(username,password);
        newuser.setUsername(fl);
        assertTrue(newuser.getUsername().equals(fl));
    }

    @Test
    public void testGetPassword() {
        User newuser = new User(username,password);
        assertTrue(newuser.getPassword().equals(password));
    }

    @Test
    public void testSetPassword() {
        User newuser = new User(username,password);
        newuser.setPassword(fl);
        assertTrue(newuser.getPassword().equals(fl));
    }

    @Test
    public void testGetID() {
        User newuser = new User(10,username,password,100);
        assertTrue(newuser.getId() == 10);
    }

    @Test
    public void testSetID() {
        User newuser = new User(10,username,password,100);
        newuser.setId(15);
        assertTrue(newuser.getId() == 15);

    }

    @Test
    public void testGetHighScore() {
        User newuser = new User(10,username,password,100);
        assertTrue(newuser.getHighScore() == 100);
    }

    @Test
    public void testSetHighScore() {
        User newuser = new User(10,username,password,100);
        newuser.setHighScore(150);
        assertTrue(newuser.getHighScore() == 150);

    }




}
