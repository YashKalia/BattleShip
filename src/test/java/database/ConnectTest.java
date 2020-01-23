package database;

import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//
//import database.Connect;
//import entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import entity.User;
//import java.sql.SQLException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//
public class ConnectTest {
    //
//    private transient User user;
//    private transient int score;
//    private transient String regSuccess;
//    private transient Connect connection;
//
//    /**
//     *  Initializes variables used for testing.
//     */
//    @BeforeEach
//    public void init() {
//        user = new User("Tom", "Cruise");
//        score = 10;
//        regSuccess = "Registration successful.";
//        connection = Mockito.mock(Connect.class);
//    }
//
//    @Test
//    public void registerSuccessfulTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.registerUser(user)).thenReturn(regSuccess);
//
//        assertEquals(regSuccess, connection.registerUser(user));
//    }
//
//    @Test
//    public void registerUnSuccessfulTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.registerUser(user)).thenReturn("Registration unsuccessful.");
//
//        assertEquals("Registration unsuccessful.", connection.registerUser(user));
//    }
//
//    @Test
//    public void userAlreadyExistsTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.registerUser(user))
//                .thenReturn("User already exists,please log in.");
//
//        assertEquals("User already exists,please log in.", connection.registerUser(user));
//    }
//
//    @Test
//    public void userDoesNotExistAuthTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.authenticate(user)).thenReturn("User does not exist.");
//
//        assertEquals("User does not exist.", connection.authenticate(user));
//    }
//
//    @Test
//    public void userExistsCorrectPassTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.authenticate(user)).thenReturn(regSuccess);
//
//        assertEquals(regSuccess, connection.authenticate(user));
//    }
//
//    @Test
//    public void userExistsWrongPassTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.authenticate(user))
//                .thenReturn("Incorrect password,authentication unsuccessful.");
//
//        assertEquals("Incorrect password,authentication unsuccessful.",
//                connection.authenticate(user));
//    }
//
//    @Test
//    public void userDoesExistTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.registerUser(user)).thenReturn("Registration successful.");
//        Mockito.when(connection.doesUserExist(user)).thenReturn(true);
//
//        assertEquals("Registration successful.", connection.registerUser(user));
//        assertEquals(true, connection.doesUserExist(user));
//    }
//
//    @Test
//    public void userDoesNotExistTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.doesUserExist(user)).thenReturn(false);
//
//        assertEquals(false, connection.doesUserExist(user));
//    }
//
//    @Test
//    public void addScoreTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.addScore(user, score)).thenReturn("Score added.");
//
//        assertEquals("Score added.", connection.addScore(user, score));
//    }
//
//    @Test
//    public void addScoreFailTest() throws SQLException, ClassNotFoundException {
//        Mockito.when(connection.addScore(user, score)).thenReturn("Score not added.");
//
//        assertEquals("Score not added.", connection.addScore(user, score));
//    }
//}
    @InjectMocks private Connect connect;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private Connect mockedConnect;
    @Mock private ResultSet mockedResultSet;

    //public ExpectedException exception = ExpectedException.none();
    public transient User mahira;
    public transient int score;
    public transient ResultSet resultSet;


    @BeforeEach
    public void setUp() {
        mockedConnect = Mockito.mock(Connect.class);
        mockedResultSet = Mockito.mock(ResultSet.class);
        mockStatement = Mockito.mock(Statement.class);
        //MockitoAnnotations.initMocks(this);
        //mockConnection = Mockito.mock(Connection.class);
        mahira = new User("Mahira", "pwd");
        score = 250;
    }

    @Test
    public void registerUserTest() throws Exception {

    }

    @Test
    public void testConnection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
//        when(mockConnection.doesUserExist(mahira)).thenReturn(true);
        //Mockito.when(connect.doesUserExists())
        Boolean result = connect.doesUserExist(mahira, mockedResultSet);
        Assert.assertEquals(result, true);
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
    }


}

// WEBLAB





