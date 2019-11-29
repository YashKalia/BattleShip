import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testUser() {
        User newuser = new User(new String("Shazam"),new String("Billy"));
        assertTrue(newuser.getUsername().equals("Shazam"));
        assertTrue(newuser.getPassword().equals("Billy"));

        User newuser2 = new User(15,new String("Shazam2"),new String("Billy2"),100);
        assertTrue(newuser2.getUsername().equals("Shazam2"));
        assertTrue(newuser2.getPassword().equals("Billy2"));

    }

    @Test
    public void testGetUser(){
        User newuser = new User(new String("Shazam"),new String("Billy"));
        assertTrue(newuser.getUsername().equals("Shazam"));
    }

    @Test
    public void testSetUser() {
        User newuser = new User(new String("Shazam"),new String("Billy"));
        newuser.setUsername("Flash");
        assertTrue(newuser.getUsername().equals("Flash"));
    }

    @Test
    public void testGetPassword(){
        User newuser = new User(new String("Shazam"),new String("Billy"));
        assertTrue(newuser.getPassword().equals("Billy"));
    }

    @Test
    public void testSetPassword() {
        User newuser = new User(new String("Shazam"),new String("Billy"));
        newuser.setPassword("Flash");
        assertTrue(newuser.getPassword().equals("Flash"));
    }

    @Test
    public void testGetID(){
        User newuser = new User(10,new String("Shazam"),new String("Billy"),100);
        assertTrue(newuser.getId()==10);
    }

    @Test
    public void testSetID() {
        User newuser = new User(10,new String("Shazam"),new String("Billy"),100);
        newuser.setId(15);
        assertTrue(newuser.getId()==15);

    }

    @Test
    public void testGetHighScore(){
        User newuser = new User(10,new String("Shazam"),new String("Billy"),100);
        assertTrue(newuser.getHighScore()==100);
    }

    @Test
    public void testSetHighScore() {
        User newuser = new User(10,new String("Shazam"),new String("Billy"),100);
        newuser.setHighScore(150);
        assertTrue(newuser.getHighScore()==150);

    }




}
