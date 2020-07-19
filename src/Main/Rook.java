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

        // check all four sides of the boards till it can go.
        // top
        for (int i = x; i >= 0; i--) {
            if (isPieceSafe(i, y, tileBoard)) {
                this.possibleMoves.add(new Move(i, y));
            }
        }

        // bottom
        for (int i = x; i < ROWS; i++) {
            if (isPieceSafe(i, y, tileBoard)) {
                this.possibleMoves.add(new Move(i, y));
            }
        }

        // left.
        for (int i = y; i >= 0; i--) {
            if (isPieceSafe(x, i, tileBoard)) {
                this.possibleMoves.add(new Move(x, i));
            }
        }

        // right
        for (int i = y; i < ROWS; i++) {
            if (isPieceSafe(x, i, tileBoard)) {
                this.possibleMoves.add(new Move(x, i));
            }
        }

        super.printPossibleMoves();
        this.setPossibleMoves(this.possibleMoves);
        return this.possibleMoves;
    }
}
