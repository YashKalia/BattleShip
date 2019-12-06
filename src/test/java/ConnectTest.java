import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

public class ConnectTest {

    private User user;
    private Connect connection;

    @BeforeEach
    public void init() {
        user = new User("Tom", "Cruise");
        connection = Mockito.mock(Connect.class);
    }

    @Test
    public void registerSuccessfulTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.registerUser(user)).thenReturn("Registration successful.");

        assertEquals("Registration successful.", connection.registerUser(user));
    }

    @Test
    public void registerUnSuccessfulTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.registerUser(user)).thenReturn("Registration unsuccessful.");

        assertEquals("Registration unsuccessful.", connection.registerUser(user));
    }

    @Test
    public void userAlreadyExistsTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.registerUser(user)).thenReturn("User already exists,please log in.");

        assertEquals("User already exists,please log in.", connection.registerUser(user));
    }

    @Test
    public void userDoesNotExistAuthTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.authenticate(user)).thenReturn("User does not exist.");

        assertEquals("User does not exist.", connection.authenticate(user));
    }

    @Test
    public void userExistsCorrectPassTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.authenticate(user)).thenReturn("Authentication successful.");

        assertEquals("Authentication successful.", connection.authenticate(user));
    }

    @Test
    public void userExistsWrongPassTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.authenticate(user)).thenReturn("Incorrect password,authentication unsuccessful.");

        assertEquals("Incorrect password,authentication unsuccessful.", connection.authenticate(user));
    }

    @Test
    public void userDoesExistTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.registerUser(user)).thenReturn("Registration successful.");
        Mockito.when(connection.doesUserExist(user)).thenReturn(true);

        assertEquals("Registration successful.", connection.registerUser(user));
        assertEquals(true, connection.doesUserExist(user));
    }

    @Test
    public void userDoesNotExistTest() throws SQLException, ClassNotFoundException {
        Mockito.when(connection.doesUserExist(user)).thenReturn(false);

        assertEquals(false, connection.doesUserExist(user));
    }
}
