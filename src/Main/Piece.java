package Main;

import java.util.List;
import java.util.*;

public class Piece {

    protected static Integer ROWS = 8;

    private String imagePath;
    private String color;
    private String type;

    List<Move> possibleMoves = new ArrayList<>();

    Piece(String imagePath, String color, String type) {
        this.imagePath = imagePath;
        this.color = color;
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public List<Move> getPossibleMoves() { return this.possibleMoves; }

    public void printPossibleMoves() {
        for (Move move : this.possibleMoves) {
            System.out.println(move.getX() + " " + move.getY());
        }
    }

    public String moveAction(int x, int y, Tile[][]tileBoard) {
        // get the tile on the next move board.
        Tile nextTile = tileBoard[x][y];

        if (nextTile.isTileOccupied()) {
            // if this tile has a piece. get it.
            Piece pieceOnTile = nextTile.getPiece();
        }

        return "MOVE";
    }

    public String isPieceSafe(int x, int y, Tile [][]tiles) {
        if (x >= 0 && x < ROWS && y >= 0 && y < ROWS) {
            // Get the current tile.
            Tile currentTile = tiles[x][y];
            // if the tile has a piece.
            if (currentTile.isTileOccupied()) {
                // get the piece and if enemy or not.
                Piece tilePiece = currentTile.getPiece();
                System.out.println("tilePiece" + tilePiece + "this color" + this.color);
                if (tilePiece.getColor().equals(this.color)) {
                    // same color. invalid
                    return "BLOCK";
                } else {
                    // enemy block.
                    return "ATTACK";
                }
            } else {
                return "VALID";
            }
        } else {
            return "INVALID";
        }
    }

    public void addPossibleMoves() {}
}
