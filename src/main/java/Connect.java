import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


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
    static PreparedStatement ps2 = null;

    static ResultSet rs1 = null;

    //NOTE
    //For now whenever executing void main, increment the first csv in the values-currently 3
    // so that userid which is a primary key does not throw exception on being the same.
    /**
     * Main method.
     * @param args parameters args.
     */
    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url4,username,password);
            ps1 = connection.prepareStatement("insert into projects_BattleShip.User"
                    + " values (3,?,?,?);");
            ps1.setString(1,"default");
            ps1.setString(2,"abc");
            ps1.setInt(3,0);
            int status = ps1.executeUpdate();
            if (status != 0) {
                System.out.println("Connected and query added");
            }
            ps1.close();
            connection.close();

        }   catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps1.close();
            connection.close();

        }

    }


    /**Checks if a user exists in the database or not.
     *
     * @param user The user object.
     * @return a boolean value.
     * @throws SQLException The exception thrown.
     */
    public static boolean doesUserExist(User user) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url4,username,password);
            ps2 = connection.prepareStatement("select password from"
                    + " projects_BattleShip.User"
                    + " where username=? and password=? ");
            ps2.setString(1,user.getUsername());
            ps2.setString(2,user.getPassword());
            rs1 = ps2.executeQuery();
            if (rs1.wasNull()) {
                ps2.close();
                rs1.close();
                connection.close();
                return false;
            }
            ps2.close();
            rs1.close();
            connection.close();
            return true;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps2.close();
            connection.close();
            rs1.close();
        }
        return false;
    }



}
