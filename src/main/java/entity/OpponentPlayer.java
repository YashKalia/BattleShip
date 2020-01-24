package entity;

import entity.board.Board;
import entity.ships.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("PMD.AvoidBranchingStatementAsLastInLoop")
public class OpponentPlayer {

    public static ArrayList<Square> shotSquares = new ArrayList<Square>();
    public Square right;
    public Square left;
    public Square down;
    public Square up;
    private static int counter = 0; //To prevent a possible infinite loop.
    private transient int nine = 9;
    private transient int allShipsPlaced = 4;
    private transient int seventeen = 17;
    private transient int one = 1;

    public static ArrayList<Square> getShotSquares() {
        return shotSquares;
    }

    public static void setShotSquares(ArrayList<Square> shotSquares) {
        OpponentPlayer.shotSquares = shotSquares;
    }

    public Square getRight() {
        return right;
    }

    public void setRight(Square right) {
        this.right = right;
    }

    public Square getLeft() {
        return left;
    }

    public void setLeft(Square left) {
        this.left = left;
    }

    public Square getDown() {
        return down;
    }

    public void setDown(Square down) {
        this.down = down;
    }

    public Square getUp() {
        return up;
    }

    public void setUp(Square up) {
        this.up = up;
    }

    /**
     * Randomised shot that enemy performs.
     *
     * @param playerBoard board of player.
     */
    public void enemyShot(Board playerBoard, Random randomizer) {

        while (Game.opponentTurn) {

            if (!shotSquares.isEmpty()) {
                enemyShotCoordinates(playerBoard, shotSquares.get(0).getCoordinateX(),
                        shotSquares.get(0).getCoordinateY(), randomizer);
                break;
            }

            int x = -1;
            int y = -1;

            while (!playerBoard.inRange(x, y, playerBoard)) {
                x = randomizer.nextInt(10);
                y = randomizer.nextInt(10);
            }

            Square square = playerBoard.getSquare(x, y);
            if (square.shooted) {
                continue;
            }

            Game.opponentTurn = square.shootEnemy();
            if (Game.opponentTurn) {
                shotSquares.add(square);
                if (!square.getShip().isNotDestroyed()) {
                    playerBoard.ships--;
                    shotSquares.clear();
                    enemyShot(playerBoard, new Random());
                    counter = 0;
                    return;
                }
                enemyShotCoordinates(playerBoard, x, y, new Random());
                break;
            }

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
            }
        }

    }

    /**
     * Shot that enemy performs, but not randomised.
     *
     * @param playerBoard board of player.
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void enemyShotCoordinates(Board playerBoard, int x, int y, Random randomizer) {

        while (Game.opponentTurn
                && playerBoard.inRange(x, y, playerBoard)) {

            int random = -1;
            while (random == -1) {
                random = randomizer.nextInt(4);
            }
            randomizedMove(playerBoard, x, y, random);
            return;
        }

        if (playerBoard.ships == 0) {
            System.out.println("YOU LOSE");
            // System.exit(0);

        }
    }

    /**
     * Shoot on the square above the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootUp(Board playerBoard, int x, int y, Random randomizer) {
        if (y == 0 || (playerBoard.getSquare(x, y - 1).isShooted())
                || !playerBoard.inRange(x, y, playerBoard)) {
            if (counter > seventeen) {
                enemyShot(playerBoard, new Random());
            }
            counter++;

            int random = -1;
            while (random == -1 || random == 2) {
                random = randomizer.nextInt(4);
            }
            randomizedMove(playerBoard, x, y, random);
            return;

        }
        up = playerBoard.getSquare(x, y - 1);

        Game.opponentTurn = up.shootEnemy();
        if (Game.opponentTurn) {
            shotSquares.add(up);
            if (!up.getShip().isNotDestroyed()) {
                up.shooted = true;
                playerBoard.ships--;
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (up.getCoordinateY() != 0) {
                shootUp(playerBoard, up.getCoordinateX(), up.getCoordinateY(), randomizer);
            } else {
                enemyShot(playerBoard, new Random());
            }
        }
    }


    /**
     * Shoot on the square left the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootLeft(Board playerBoard, int x, int y, Random randomizer) {

        if (x == 0 || (playerBoard.getSquare(x - 1, y).isShooted())
                || !playerBoard.inRange(x, y, playerBoard)) {

            if (counter > seventeen) {
                enemyShot(playerBoard, new Random());
            }
            counter++;

            int random = -1;
            while (random == -1 || random == 1) {
                random = randomizer.nextInt(4);
            }
            randomizedMove(playerBoard, x, y, random);
            return;

        }

        left = playerBoard.getSquare(x - 1, y);

        Game.opponentTurn = left.shootEnemy();
        if (Game.opponentTurn) {
            shotSquares.add(left);
            if (!left.getShip().isNotDestroyed()) {
                left.shooted = true;
                playerBoard.ships--;
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (left.getCoordinateX() != 0) {
                shootLeft(playerBoard, left.getCoordinateX(), left.getCoordinateY(), new Random());
            } else {
                enemyShot(playerBoard, new Random());
            }
        }
    }

    /**
     * Shoot on the square right the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootRight(Board playerBoard, int x, int y, Random randomizer) {

        if (x == 9 || (playerBoard.getSquare(x + 1, y).isShooted())
                || !playerBoard.inRange(x, y, playerBoard)) {

            if (counter > seventeen) {
                enemyShot(playerBoard, new Random());
            }
            counter++;

            int random = -1;
            while (random == -1 || random == 0) {
                random = randomizer.nextInt(4);
            }
            randomizedMove(playerBoard, x, y, random);
            return;
        }
        right = playerBoard.getSquare(x + 1, y);

        Game.opponentTurn = right.shootEnemy();
        if (Game.opponentTurn) {
            shotSquares.add(right);
            right.shooted = true;
            if (!right.getShip().isNotDestroyed()) {
                playerBoard.ships--;
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (right.getCoordinateX() != nine) {
                shootRight(playerBoard, right.getCoordinateX(), right.getCoordinateY(),
                        new Random());

            } else {
                enemyShot(playerBoard, new Random());
            }
        }
    }


    /**
     * Shoot on the square down the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootDown(Board playerBoard, int x, int y, Random randomizer) {

        if (y == nine || (playerBoard.getSquare(x, y + 1).isShooted())
                || !playerBoard.inRange(x, y, playerBoard)) {
            if (counter > seventeen) {
                enemyShot(playerBoard, new Random());
            }
            counter++;

            int random = -1;
            while (random == -1 || random == 3) {
                random = randomizer.nextInt(4);
            }

            randomizedMove(playerBoard, x, y, random);
            return;
        }

        down = playerBoard.getSquare(x, y + 1);

        Game.opponentTurn = down.shootEnemy();

        if (Game.opponentTurn) {
            shotSquares.add(down);
            if (!down.getShip().isNotDestroyed()) {
                playerBoard.ships--;
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (down.getCoordinateY() != nine) {
                shootDown(playerBoard, down.getCoordinateX(), down.getCoordinateY(), new Random());
            } else {
                enemyShot(playerBoard, new Random());
            }
        }
    }

    /**
     * Randomly select which method should be executed.
     * @param playerBoard Board is played on.
     * @param x The X-coordinate
     * @param y The Y-coordinate
     * @param random The randomized number passed.
     */
    public void randomizedMove(Board playerBoard, int x, int y, int random) {
        switch (random) {
            case 0:
                shootRight(playerBoard, x, y, new Random());
                return;
            case 1:
                shootLeft(playerBoard, x, y, new Random());
                return;
            case 2:
                shootUp(playerBoard, x, y, new Random());
                return;
            case 3:
                shootDown(playerBoard, x, y, new Random());
                return;
            default:
                break;
        }
    }

    /**
     * method to place the opponent ships.
     * @param opponentBoard board of the opponent.
     */
    public void placeShipsOpponent(Board opponentBoard, Random randomizer) {
        List<Ship> ships = Board.makeListWithShips();
        while (!ships.isEmpty()) {

            randomizer = new Random();

            int x = -1;
            int y = -1;

            while (!opponentBoard.inRange(x, y, opponentBoard)) {
                x = randomizer.nextInt(10);
                y = randomizer.nextInt(10);
            }

            int orientation = randomizer.nextInt(2);
            if (orientation == 0) {
                ships.get(allShipsPlaced).orientation = false;
            }
            if (orientation == one) {
                ships.get(allShipsPlaced).orientation = true;
            }

            if (opponentBoard.placeShip(ships.get(allShipsPlaced), x, y, opponentBoard)) {
                allShipsPlaced--;
            }
            if (allShipsPlaced < 0) {
                return;
            }
        }
    }
}
