package entity;

public class User {

    private String username;
    private String password;
    private int highScore;
    private int id;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
