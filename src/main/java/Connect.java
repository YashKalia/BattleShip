import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {

    static Connection connection;
    static String url2 = "https://projects.ewi.tudelft.nl/BattleShip";
    static String url3 =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/BattleShip?"
                    + "useTimezone=true&serverTimezone=UTC";
    static String url4 =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_BattleShip?"
                    + "useTimezone=true&serverTimezone=UTC";

    static String password = "Cd5vH9NMZc84";
    static String username = "pu_BattleShip";

    static PreparedStatement ps1 = null;
    static PreparedStatement ps4 = null;
    static Statement ps2 = null;
    static Statement ps3 = null;


    static ResultSet rs1 = null;
    static ResultSet rs2 = null;

    //NOTE
    //For now whenever executing void main, increment the first csv in the values-currently 3
    // so that userid which is a primary key does not throw exception on being the same.

    /**
     * Main method.
     *
     * @param args parameters args.
     */
    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url4, username, password);
            // ps1 = connection.prepareStatement("insert into projects_BattleShip.User"
            //  + " values (3,?,?,?);");
            // ps1.setString(1,"default");
            // ps1.setString(2,"abc");
            // ps1.setInt(3,0);
            User newuser = new User(new String("Yash"), new String("password"));
            System.out.println(authenticate(newuser));
            //int status = ps1.executeUpdate();
            //if (status != 0) {
            //   System.out.println("Connected and query added");
            //}
            //ps1.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //ps1.close();
            connection.close();

        }

    }

    /**
     * Register a user to the database.
     *
     * @param user The user object.
     * @return a String that will be printed on the screen.
     * @throws SQLException If error occurs.
     */
    public static String registerUser(User user) throws SQLException {
        if (!doesUserExist(user)) {
            ps4 = connection.prepareStatement(
                    "insert into projects_BattleShip.User values (?,?,?,0);");
            ps4.setInt(1, 5);
            ps4.setString(2, user.getUsername());
            ps4.setString(3, user.getPassword());
            int status = ps4.executeUpdate();
            if (status != 0) {
                return new String("Registration successful.");
            } else {
                return new String("Registration unsuccessful.");
            }
        } else {
            return new String("User already exists,please log in.");
        }
    }


    /**
     * Authentication of user.
     *
     * @param user the user object made when user enters his/her details.
     * @return a String which will be printed on the screen.
     * @throws SQLException IF error occurs.
     */
    public static String authenticate(User user) throws SQLException {

        if (!doesUserExist(user)) {
            return new String("User does not exist.");
        } else {
            connection = DriverManager.getConnection(url4, username, password);
            ps3 = connection.createStatement();
            rs2 = ps3.executeQuery("select password from projects_BattleShip.User where"
                    + " username='" + user.getUsername() + "';");
            rs2.next();
            String password = (rs2.getString("password"));
            if (password.equals(user.getPassword())) {
                return new String("Authentication successful.");
            } else {
                return new String("Incorrect password,authentication unsuccessful.");
            }

        }

    }


    /**
     * Checks if a user exists in the database or not.
     *
     * @param user The user object.
     * @return a boolean value.
     * @throws SQLException The exception thrown.
     */
    public static boolean doesUserExist(User user) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url4, username, password);
            ps2 = connection.createStatement();


            rs1 = ps2.executeQuery("select password from"
                    + " projects_BattleShip.User"
                    + " where username='" + user.getUsername() + "';");
            if (rs1.next()) {
                ps2.close();
                rs1.close();
                connection.close();
                return true;
            } else {
                ps2.close();
                rs1.close();
                connection.close();
                return false;
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps2.close();
            connection.close();
            //rs1.close();
        }
        return false;
    }






}
