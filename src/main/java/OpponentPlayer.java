import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpponentPlayer {
    public transient Random random;
    public static ArrayList<Square> shotSquares = new ArrayList<Square>();
    public Square right;
    public Square left;
    public Square down;
    public Square up;

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

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
    protected void enemyShot(Board playerBoard, Random randomizer) {

        while (HelloWorld.opponentTurn) {

            if (!shotSquares.isEmpty()) {
                enemyShotCoordinates(playerBoard, shotSquares.get(0).getCoordinateX(),
                        shotSquares.get(0).getCoordinateY(), randomizer);
                break;
            }

            int x = randomizer.nextInt(10);
            int y = randomizer.nextInt(10);

            Square square = playerBoard.getSquare(x, y);
            if (square.shooted) {
                continue;
            }

            HelloWorld.opponentTurn = square.shootEnemy();
            if (HelloWorld.opponentTurn) {
                shotSquares.add(square);
                if (!square.getShip().isNotDestroyed()) {
                    shotSquares.clear();
                    enemyShot(playerBoard, new Random());
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
    protected void enemyShotCoordinates(Board playerBoard, int x, int y, Random randomizer) {
        while (HelloWorld.opponentTurn) {

//            Random random2 = new Random();
//            setRandom(random2);
            int random = randomizer.nextInt(4);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootLeft(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                case 3:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                default:
                    break;
            }
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
        if (y == 0) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootLeft(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
        up = playerBoard.getSquare(x, y - 1);

        if (up.shooted) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootLeft(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
        HelloWorld.opponentTurn = up.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(up);
            if (!up.getShip().isNotDestroyed()) {
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

        if (x == 0) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootUp(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
        left = playerBoard.getSquare(x - 1, y);

        if (left.shooted) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootRight(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
        HelloWorld.opponentTurn = left.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(left);
            if (!left.getShip().isNotDestroyed()) {
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

        int nine = 9; //PMD purposes.

        if (x == nine) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootLeft(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
        right = playerBoard.getSquare(x + 1, y);

        if (right.shooted || (!playerBoard.inRange(right.getCoordinateX(),
                right.getCoordinateY()))) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    return;
                case 1:
                    shootLeft(playerBoard, x, y, new Random());
                    return;
                case 2:
                    shootDown(playerBoard, x, y, new Random());
                    return;
                default:
                    break;
            }
        }
//        right.shooted  = true;
        HelloWorld.opponentTurn = right.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(right);
            if (!right.getShip().isNotDestroyed()) {
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (right.getCoordinateX() != 9) {
                shootRight(playerBoard, right.getCoordinateX(), right.getCoordinateY(), new Random());
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

        int nine = 9; //PMD purposes.
        if (y == nine) {
            int random = randomizer.nextInt(3);
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
                default:
                    break;
            }
        }
        down = playerBoard.getSquare(x, y + 1);

        if (down.shooted) {
            int random = randomizer.nextInt(3);
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
                default:
                    break;
            }
        }
        HelloWorld.opponentTurn = down.shootEnemy();

        if (HelloWorld.opponentTurn) {
            shotSquares.add(down);
            if (!down.getShip().isNotDestroyed()) {
                shotSquares.clear();
                enemyShot(playerBoard, new Random());
                return;
            }
            if (down.getCoordinateY() != 9) {
                shootDown(playerBoard, down.getCoordinateX(), down.getCoordinateY(), new Random());
            } else {
                enemyShot(playerBoard, new Random());
            }
        }
    }


    /**
     * method to place the opponent ships.
     *
     * @param opponentBoard board of the opponent.
     */
    @SuppressWarnings("PMD")
    protected void placeShipsOpponent(Board opponentBoard) {
        List<Ship> ships = Board.makeListWithShips();

        int allShipsPlaced = 4;
        while (!ships.isEmpty()) {

            Random random10 = new Random();
            setRandom(random10);
            int x = random10.nextInt(10);
            int y = random10.nextInt(10);

            if (opponentBoard.placeShip(ships.get(allShipsPlaced), x, y)) {
                allShipsPlaced--;
            }
            if (allShipsPlaced < 0) {
                return;
            }
        }
    }
}