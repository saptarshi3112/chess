package Main;

import java.util.List;
import java.util.*;

public class Piece {

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

    public boolean isPieceSafe(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public List<Move> getPossibleMoves() { return this.possibleMoves; }
}
