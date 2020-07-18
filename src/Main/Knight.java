package Main;

import java.util.*;

public class Knight extends Piece {

    // Constructor
    Knight(String imagePath, String color, String type) {
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

        int []xRange = {x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
        int []yRange = {y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};

        for (int i = 0; i < 8; i++) {
            int nextMoveX = xRange[i];
            int nextMoveY = yRange[i];

            System.out.println(nextMoveX + " " + nextMoveY);

            if (super.isPieceSafe(nextMoveX, nextMoveY)) {
                possibleMoves.add(new Move(nextMoveX, nextMoveY));
            }

        }

        this.setPossibleMoves(this.possibleMoves);
        return possibleMoves;

    }

}
