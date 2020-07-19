package Main;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    // Constructor
    Rook(String imagePath, String color, String type) {
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

//         check all four sides of the boards till it can go.
//         top
        for (int i = x-1; i >= 0; i--) {
            String action = super.isPieceSafe(i, y, tileBoard);
            System.out.println(action);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, y, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, y, true, false));
                break;
            } else if (action.equals("BLOCK")) {
                // a friend piece is present. break the loop.
                break;
            } else if (action.equals("INVALID")) {
                System.out.println("Invalid");
            }
        }

        // bottom
        for (int i = x+1; i < ROWS; i++) {
            System.out.println(i  + " " + y);
            String action = super.isPieceSafe(i, y, tileBoard);
            System.out.println(action);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(i, y, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(i, y, true, false));
                break;
            } else if (action.equals("BLOCK")) {
                // a friend piece is present. break the loop.
                break;
            } else if (action.equals("INVALID")) {
                System.out.println("Invalid");
            }
        }

        // left.
        for (int i = y-1; i >= 0; i--) {
            String action = super.isPieceSafe(x, i, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(x, i, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(x, i, true, false));
                break;
            } else if (action.equals("BLOCK")) {
                // a friend piece is present. break the loop.
                break;
            } else if (action.equals("INVALID")) {
                System.out.println("Invalid");
            }
        }

//         right
        for (int i = y+1; i < ROWS; i++) {
            String action = super.isPieceSafe(x, i, tileBoard);
            if (action.equals("VALID")) {
                possibleMoves.add(new Move(x, i, false, true));
            } else if (action.equals("ATTACK")) {
                possibleMoves.add(new Move(x, i, true, false));
                break;
            } else if (action.equals("BLOCK")) {
                break;
            }
        }

        super.printPossibleMoves();
        this.setPossibleMoves(this.possibleMoves);
        return this.possibleMoves;
    }
}
