import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {

    static Connection connection1;
    static Connection connection2;
    static Connection connection3;
    static Connection connection4;
    static Connection connection5;

    static String url4 =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_BattleShip?"
                    + "useTimezone=true&serverTimezone=UTC";
    static String driver = "com.mysql.cj.jdbc.Driver";

    static String password = "Cd5vH9NMZc84";
    static String username = "pu_BattleShip";

    static PreparedStatement ps1 = null;
    static PreparedStatement ps4 = null;
    static PreparedStatement ps5 = null;
    static Statement ps6 = null;
    static Statement ps2 = null;
    static Statement ps3 = null;


    static ResultSet rs1 = null;
    static ResultSet rs2 = null;
    static ResultSet rs3 = null;
    static ResultSet rs4 = null;


    /**
     * Main method.
     *
     * @param args parameters args.
     */
    public void main(String[] args) throws SQLException, ClassNotFoundException {

        //User newuser = new User(new String("Ice"), new String("Cube"))
        try {
            rs4 = getTopFive();
            while (rs4.next()) {
                for (int i = 1;i <= rs4.getMetaData().getColumnCount();i++) {
                    System.out.println(rs4.getInt(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs4.close();
        }
    }

    /**
     * Register a user to the database.
     *
     * @param user The user object.
     * @return a String that will be printed on the screen.
     * @throws SQLException If error occurs.
     */
    public String registerUser(User user) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection1 = DriverManager.getConnection(url4, username, password);
        if (!doesUserExist(user)) {
            ps4 = connection1.prepareStatement(
                    "insert into projects_BattleShip.User ("
                            + "username,password,highscore) values (?,?,0);");
            ps4.setString(1, user.getUsername());
            ps4.setString(2, user.getPassword());
            int status1 = ps4.executeUpdate();
            if (status1 != 0) {
                connection1.close();
                return new String("Registration successful.");
            } else {
                connection1.close();
                return new String("Registration unsuccessful.");
            }
        } else {
            connection1.close();
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
    public String authenticate(User user) throws SQLException, ClassNotFoundException {
        if (!doesUserExist(user)) {
            return new String("User does not exist.");
        } else {
            Class.forName(driver);
            connection2 = DriverManager.getConnection(url4, username, password);
            ps3 = connection2.createStatement();

            rs2 = ps3.executeQuery("select password from projects_BattleShip.User where"
                    + " username='" + user.getUsername() + "';");
            rs2.next();
            String password = (rs2.getString("password"));
            if (password.equals(user.getPassword())) {
                connection2.close();
                return new String("Authentication successful.");
            } else {
                connection2.close();
                return new String("Incorrect password,authentication unsuccessful.");
            }

        }

    }

    /**
     * Return top 5 high scores from the database.
     * @return a ResultSet.
     * @throws ClassNotFoundException if class not found.
     * @throws SQLException if query is incorrect.
     */
    public ResultSet getTopFive() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection5 = DriverManager.getConnection(url4, username, password);
        ps6 = connection5.createStatement();
        rs3 = ps6.executeQuery("select highscore from"
                + " projects_BattleShip.User"
                + " order by highscore desc limit 5;");
        return rs3;
    }


    /**
     * Checks if a user exists in the database or not.
     *
     * @param user The user object.
     * @return a boolean value.
     * @throws SQLException The exception thrown.
     */
    public boolean doesUserExist(User user) throws SQLException {
        try {
            Class.forName(driver);
            connection3 = DriverManager.getConnection(url4, username, password);
            ps2 = connection3.createStatement();

            rs1 = ps2.executeQuery("select password from"
                    + " projects_BattleShip.User"
                    + " where username='" + user.getUsername() + "';");
            if (rs1.next()) {
                ps2.close();
                rs1.close();
                connection3.close();
                return true;
            } else {
                ps2.close();
                rs1.close();
                connection3.close();
                return false;
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps2.close();
            connection3.close();
            rs1.close();
        }
        return false;
    }


    /**Whenever a user finishes a game their score is added to their list of scores.
     *
     * @param user The user object.
     * @param score The score of a game.
     * @return a String description of success or not.
     * @throws SQLException if error occurs.
     * @throws ClassNotFoundException if error occurs.
     */
    public String addScore(User user,int score) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection4 = DriverManager.getConnection(url4, username, password);
        ps5 = connection4.prepareStatement(
                "insert into projects_BattleShip.Score (id,scores) values (?,?);");

        ps5.setInt(1, user.getId());
        ps5.setInt(2, score);

        int status1 = ps5.executeUpdate();

        if (status1 != 0) {
            connection4.close();
            return new String("Score added.");
        } else {
            connection4.close();
            return new String("Score not added.");
        }
    }


    //Need a method to check if the score being added is a high score or not.





}
