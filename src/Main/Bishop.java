package Main;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    // Constructor.
    Bishop(String imagePath, String color, String type) {
        super(imagePath, color, type);
    }

    /**
     *
     * This method will calculate all the possible moves a piece can have.
     * It takes the tile board and finds how many enemies are present in its moves.
     *
     */
    List<Move> getAllPossibleMoves(Tile [][]tileBoard, int x, int y) throws NullPointerException {
        this.possibleMoves = new ArrayList<>();

        // four diagonals.
        int i = x+1, j = y+1;

        // bottom right
        while (i < ROWS && j < ROWS) {
            String action = super.isPieceSafe(i, j, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, j, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, j, true, false));
            } else if (action.equals("BLOCK")) {
                break;
            }
            i += 1;
            j += 1;
        }

        i = x-1;
        j = y-1;

        while (i >= 0 && j >= 0) {
            String action = super.isPieceSafe(i, j, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, j, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, j, true, false));
            } else if (action.equals("BLOCK")) {
                break;
            }
            i -= 1;
            j -= 1;
        }

        // top left.
        i = x-1;
        j = y+1;

        while (i >= 0 && j < ROWS) {
            String action = super.isPieceSafe(i, j, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, j, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, j, true, false));
            } else if (action.equals("BLOCK")) {
                break;
            }
            i -= 1;
            j += 1;
        }

        i = x+1;
        j = y-1;
        while (i < ROWS && j >= 0) {
            String action = super.isPieceSafe(i, j, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, j, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, j, true, false));
            } else if (action.equals("BLOCK")) {
                break;
            }

            i += 1;
            j -= 1;

        }

        this.setPossibleMoves(this.possibleMoves);
        super.printPossibleMoves();
        return this.possibleMoves;
    }
}
