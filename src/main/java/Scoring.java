import javafx.geometry.Point2D;

/**
 * Class Score, specifies the amount of points the player has obtained.
 */
public class Scoring {
    int score = 0;

    /**
     * Getting the score of the player.
     *
     * @return The amount of points the player has achieved.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setting the score of the player.
     *
     * @param score The amount of points you want the player to have.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This method will give the amount of points obtained by discovering a ship based on the
     * first part of the ship that is discovered.
     *
     * @param coordinate The coordinate on which a click event has occurred containing
     *                   a piece of a ship.
     * @param board      The board the player is playing on.
     * @param ship       The ship the square on which the click event occurred has.
     * @return The amount of points the player has achieved by that ship.
     */
    public int scoreSystem(Point2D coordinate, Board board, Ship ship) {

        String shipname = ship.getShipName();
        switch (shipname) {

            case "Carrier":
                Point2D frontCarrier = board.frontShip.get("Carrier");
                if (coordinate.equals(frontCarrier)
                        || coordinate.equals(new Point2D(frontCarrier.getX(), (frontCarrier.getY()
                        + (ship.getTypeShip() - 1))))
                        || coordinate.equals(new Point2D(frontCarrier.getX()
                        + (ship.getTypeShip() - 1),
                        (frontCarrier.getY())))) {
                    score += (3000) / board.getMisses();
                    return score;
                }
                if (coordinate.equals(new Point2D(frontCarrier.getX(), (frontCarrier.getY()
                        + (ship.getTypeShip() - 2))))
                        || coordinate.equals(new Point2D(frontCarrier.getX()
                        + (ship.getTypeShip() - 2),
                        (frontCarrier.getY())))
                        || coordinate.equals(new Point2D(frontCarrier.getX(), (frontCarrier.getY()
                        + (ship.getTypeShip() - 4))))
                        || coordinate.equals(new Point2D(frontCarrier.getX()
                        + (ship.getTypeShip() - 4), (frontCarrier.getY())))) {
                    score += (4000) / board.getMisses();
                    return score;

                } else {
                    score += (5000) / board.getMisses();
                    return score;
                }

            case "BattleShip":
                Point2D frontBattleShip = board.frontShip.get("BattleShip");
                if (coordinate.equals(new Point2D(frontBattleShip.getX(), (frontBattleShip.getY()
                        + (ship.getTypeShip() - 1))))
                        || coordinate.equals(new Point2D(frontBattleShip.getX()
                        + (ship.getTypeShip() - 1),
                        (frontBattleShip.getY())))) {
                    score += (3000) / board.getMisses();
                    return score;
                }
                if (coordinate.equals(new Point2D(frontBattleShip.getX(), (frontBattleShip.getY()
                        + (ship.getTypeShip() - 2))))
                        || coordinate.equals(new Point2D(frontBattleShip.getX(),
                        (frontBattleShip.getY()
                                + (ship.getTypeShip() - 3))))
                        || coordinate.equals(new Point2D(frontBattleShip.getX()
                        + (ship.getTypeShip() - 2),
                        (frontBattleShip.getY())))
                        || coordinate.equals(new Point2D(frontBattleShip.getX()
                        + (ship.getTypeShip() - 3),
                        (frontBattleShip.getY())))) {
                    score += (1000) / board.getMisses();
                    return score;
                } else {
                    score += (4000) / board.getMisses();
                    return score;
                }

            case "Cruiser":
                Point2D frontCruiser = board.frontShip.get("Cruiser");
                if (coordinate.equals(frontCruiser)) {
                    score += (1000) / board.getMisses();
                    return score;
                }
                if (coordinate.equals(new Point2D(frontCruiser.getX(), (frontCruiser.getY()
                        + (ship.getTypeShip() - 1))))
                        || coordinate.equals(new Point2D(frontCruiser.getX()
                        + (ship.getTypeShip() - 1),
                        (frontCruiser.getY())))) {
                    score += (2000) / board.getMisses();
                    return score;
                } else {
                    score += (3000) / board.getMisses();
                    return score;
                }

            case "Submarine":
                Point2D frontSubmarine = board.frontShip.get("Submarine");
                if (coordinate.equals(frontSubmarine)) {
                    score += (3000) / board.getMisses();
                    return score;
                }
                if (coordinate.equals(new Point2D(frontSubmarine.getX(), (frontSubmarine.getY()
                        + (ship.getTypeShip() - 2))))
                        || coordinate.equals(new Point2D(frontSubmarine.getX()
                        + (ship.getTypeShip() - 2),
                        (frontSubmarine.getY())))) {
                    score += (2000) / board.getMisses();
                    return score;
                } else {
                    score += (3500) / board.getMisses();
                    return score;
                }

            case "Destroyer":
                Point2D frontDestroyer = board.frontShip.get("Destroyer");
                if (coordinate.equals(frontDestroyer)) {
                    score += (1500) / board.getMisses();
                    return score;
                } else {
                    score += (2000) / board.getMisses();
                    return score;
                }

            default:
                throw new IllegalStateException("Ship does not Exist: " + shipname);
        }
    }
}