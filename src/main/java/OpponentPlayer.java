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
    protected void enemyShot(Board playerBoard) {
        while (HelloWorld.opponentTurn) {

            if (!shotSquares.isEmpty()) {
                enemyShotCoordinates(playerBoard, shotSquares.get(0).getCoordinateX(),
                        shotSquares.get(0).getCoordinateY(), new Random());
                break;
            }
            random = new Random();
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square square = playerBoard.getSquare(x, y);
            if (square.shooted) {
                continue;
            }

            HelloWorld.opponentTurn = square.shootEnemy();
            if (HelloWorld.opponentTurn) {
                shotSquares.add(square);
                if (!square.getShip().isNotDestroyed()) {
                    shotSquares.clear();
                }
                enemyShotCoordinates(playerBoard, x, y, new Random());
            }

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);

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
                    shootRight(playerBoard, x, y);
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootDown(playerBoard, x, y);
                    break;
                case 3:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
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
//
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    return;
                case 1:
                    shootLeft(playerBoard, x, y);
                    return;
                case 2:
                    shootDown(playerBoard, x, y);
                    return;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    return;
            }
        }
        up = playerBoard.getSquare(x, y - 1);

        if (up.shooted) {
            int random = randomizer.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootDown(playerBoard, x, y);
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;

            }
        }
        HelloWorld.opponentTurn = up.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(up);
            if (!up.getShip().isNotDestroyed()) {
                shotSquares.clear();
            }
            shootUp(playerBoard, up.getCoordinateX(), up.getCoordinateY(), new Random());
        } else {
            enemyShot(playerBoard);
        }
    }


    /**
     * Shoot on the square left the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootLeft(Board playerBoard, int x, int y) {

        if (x == 0) {
            Random random5 = new Random();
            setRandom(random5);
            int random = random5.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    break;
                case 1:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                case 2:
                    shootDown(playerBoard, x, y);
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
        left = playerBoard.getSquare(x - 1, y);

        if (left.shooted || (!playerBoard.inRange(left.getCoordinateX(),
                left.getCoordinateY()))) {
            Random random6 = new Random();
            setRandom(random6);
            int random = random6.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                case 1:
                    shootDown(playerBoard, x, y);
                    break;
                case 2:
                    shootRight(playerBoard, x, y);
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
        HelloWorld.opponentTurn = left.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(left);
            if (!left.getShip().isNotDestroyed()) {
                shotSquares.clear();
            }
            shootLeft(playerBoard, left.getCoordinateX(), left.getCoordinateY());
        } else {
            enemyShot(playerBoard);
        }
    }

    /**
     * Shoot on the square right the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootRight(Board playerBoard, int x, int y) {

        int nine = 9; //PMD purposes.

        if (x == nine) {
            Random random7 = new Random();
            setRandom(random7);
            int random = random7.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootDown(playerBoard, x, y);
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
        right = playerBoard.getSquare(x + 1, y);

        if (right.shooted || (!playerBoard.inRange(right.getCoordinateX(),
                right.getCoordinateY()))) {
            Random random8 = new Random();
            setRandom(random8);
            int random = random8.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootDown(playerBoard, x, y);
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
//        right.shooted  = true;
        HelloWorld.opponentTurn = right.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(right);
            if (!right.getShip().isNotDestroyed()) {
                shotSquares.clear();
            }
            shootRight(playerBoard, right.getCoordinateX(), right.getCoordinateY());
        } else {
            enemyShot(playerBoard);
        }
    }


    /**
     * Shoot on the square down the locations given.
     *
     * @param playerBoard board of the player
     * @param x           coordinate of square location
     * @param y           coordinate of square location
     */
    public void shootDown(Board playerBoard, int x, int y) {

        int nine = 9; //PMD purposes.
        if (y == nine) {
            Random random9 = new Random();
            setRandom(random9);
            int random = random9.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
        down = playerBoard.getSquare(x, y + 1);

        if (down.shooted || (!playerBoard.inRange(down.getCoordinateX(),
                down.getCoordinateY()))) {
            Random random10 = new Random();
            setRandom(random10);
            int random = random10.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    break;
                case 1:
                    shootLeft(playerBoard, x, y);
                    break;
                case 2:
                    shootUp(playerBoard, x, y, new Random());
                    break;
                default:
                    enemyShotCoordinates(playerBoard, x, y, new Random());
                    break;
            }
        }
        HelloWorld.opponentTurn = down.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(down);
            if (!down.getShip().isNotDestroyed()) {
                shotSquares.clear();
            }
            shootDown(playerBoard, down.getCoordinateX(), down.getCoordinateY());
        } else {
            enemyShot(playerBoard);
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