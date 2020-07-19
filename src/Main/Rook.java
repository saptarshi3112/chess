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
    List<Move> getAllPossibleMoves(Tile [][]tileBoard, int x, int y) {
        this.possibleMoves = new ArrayList<>();

        // check all four sides of the board.



        return this.possibleMoves;
    }
}
