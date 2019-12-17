import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpponentPlayer {
    private transient Random random;
    private transient Random random2;
    private transient Random random3;
    private transient Random random4;
    private transient Random random5;
    private transient Random random6;
    private transient Random random7;
    private transient Random random8;
    private transient Random random9;
    private transient Random random10;
    private transient int seventeen = 17; //pmd purposes.
    private transient int nine = 9; //pmd purposes
    private transient int allShipsPlaced = 4; //pmd purposes.
    public static ArrayList<Square> shotSquares = new ArrayList<>();
    private static int counter = 0; //To prevent a possible infinite loop.

    /**
     * Randomised shot that enemy performs.
     *
     * @param playerBoard board of player.
     */
    protected void enemyShot(Board playerBoard) {

        while (HelloWorld.opponentTurn) {

            if (!shotSquares.isEmpty()) {
                enemyShotCoordinates(playerBoard, shotSquares.get(0).getCoordinateX(),
                        shotSquares.get(0).getCoordinateY());
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
                    counter = 0;
                    enemyShot(playerBoard);
                    return;
                }
                enemyShotCoordinates(playerBoard, x, y);
                break;
            }

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");

                // System.exit(0);

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
    protected void enemyShotCoordinates(Board playerBoard, int x, int y) {
        while (HelloWorld.opponentTurn) {

            random2 = new Random();
            int random = random2.nextInt(4);
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
                case 3:
                    shootUp(playerBoard, x, y);
                    return;
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
    public void shootUp(Board playerBoard, int x, int y) {

        if (y == 0) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random3 = new Random();
            int random = random3.nextInt(3);
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
                    break;
            }
        }
        Square square = playerBoard.getSquare(x, y - 1);

        if (square.shooted || (!playerBoard.inRange(square.getCoordinateX(),
                square.getCoordinateY()))) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random4 = new Random();
            int random = random4.nextInt(3);
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
                    break;

            }
        }
        HelloWorld.opponentTurn = square.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(square);
            if (!square.getShip().isNotDestroyed()) {
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard);
                return;
            }
            if (square.getCoordinateY() != 0) {
                shootUp(playerBoard, square.getCoordinateX(), square.getCoordinateY());
            } else {
                enemyShot(playerBoard);
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
    public void shootLeft(Board playerBoard, int x, int y) {

        if (x == 0) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random5 = new Random();
            int random = random5.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    return;
                case 1:
                    shootUp(playerBoard, x, y);
                    return;
                case 2:
                    shootDown(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }
        Square square = playerBoard.getSquare(x - 1, y);

        if (square.shooted || (!playerBoard.inRange(square.getCoordinateX(),
                square.getCoordinateY()))) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random6 = new Random();
            int random = random6.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y);
                    return;
                case 1:
                    shootDown(playerBoard, x, y);
                    return;
                case 2:
                    shootRight(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }
        HelloWorld.opponentTurn = square.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(square);
            if (!square.getShip().isNotDestroyed()) {
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard);
                return;
            }
            if (square.getCoordinateX() != 0) {
                shootLeft(playerBoard, square.getCoordinateX(), square.getCoordinateY());
            } else {
                enemyShot(playerBoard);
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
    public void shootRight(Board playerBoard, int x, int y) {

        if (x == nine) {
            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random7 = new Random();
            int random = random7.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y);
                    return;
                case 1:
                    shootLeft(playerBoard, x, y);
                    return;
                case 2:
                    shootDown(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }
        Square square = playerBoard.getSquare(x + 1, y);

        if (square.shooted || (!playerBoard.inRange(square.getCoordinateX(),
                square.getCoordinateY()))) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random8 = new Random();
            int random = random8.nextInt(3);
            switch (random) {
                case 0:
                    shootUp(playerBoard, x, y);
                    return;
                case 1:
                    shootLeft(playerBoard, x, y);
                    return;
                case 2:
                    shootDown(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }
        HelloWorld.opponentTurn = square.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(square);
            if (!square.getShip().isNotDestroyed()) {
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard);
                return;
            }
            if (square.getCoordinateX() != nine) {
                shootRight(playerBoard, square.getCoordinateX(), square.getCoordinateY());
            } else {
                enemyShot(playerBoard);
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
    public void shootDown(Board playerBoard, int x, int y) {

        if (y == nine) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random9 = new Random();
            int random = random9.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    return;
                case 1:
                    shootLeft(playerBoard, x, y);
                    return;
                case 2:
                    shootUp(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }
        Square square = playerBoard.getSquare(x, y + 1);

        if (square.shooted || (!playerBoard.inRange(square.getCoordinateX(),
                square.getCoordinateY()))) {

            if (counter > seventeen) {
                enemyShot(playerBoard);
            }
            counter++;

            random10 = new Random();
            int random = random10.nextInt(3);
            switch (random) {
                case 0:
                    shootRight(playerBoard, x, y);
                    return;
                case 1:
                    shootLeft(playerBoard, x, y);
                    return;
                case 2:
                    shootUp(playerBoard, x, y);
                    return;
                default:
                    break;
            }
        }

        HelloWorld.opponentTurn = square.shootEnemy();
        if (HelloWorld.opponentTurn) {
            shotSquares.add(square);
            if (!square.getShip().isNotDestroyed()) {
                counter = 0;
                shotSquares.clear();
                enemyShot(playerBoard);
                return;
            }
            if (square.getCoordinateY() != nine) {
                shootDown(playerBoard, square.getCoordinateX(), square.getCoordinateY());
            } else {
                enemyShot(playerBoard);
            }
        }
    }


    /**
     * method to place the opponent ships.
     *
     * @param opponentBoard board of the opponent.
     */
    protected void placeShipsOpponent(Board opponentBoard) {
        List<Ship> ships = Board.makeListWithShips();


        while (!ships.isEmpty()) {

            random10 = new Random();
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


