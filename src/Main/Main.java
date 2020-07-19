package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.List;

public class Main extends JFrame implements MouseListener {

    private static final Integer ROWS = 8;
    private static final Integer COLS = 8;

    // This matrix will contain all the config of the board at any point.
    private final Tile[][] tileBoard = new Tile[ROWS][COLS];

    private static Knight blackKnight, blackKnight2;

    private static Tile previousTile = null;
    private static Piece previousClick = null;

    // Constructor.
    Main() {

        // This panel will contain the grid.
        JPanel board = new JPanel();

        // Set a grid for the layout.
        board.setLayout(new GridLayout(ROWS, COLS));

        // create the initial board.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                Piece tempPiece = null;

                if (i == 0) {
                    if (j == 1)
                        tempPiece = blackKnight;
                    if (j == 6)
                        tempPiece = blackKnight2;
                }

                // create a new tile.
                Tile tile = new Tile(i, j, tempPiece);

                this.tileBoard[i][j] = tile;
                board.add(tile);

                tile.addMouseListener(this);

            }
        }

        // this layout will contain all the pieces.
        this.setFocusable(true);
        this.add(board);
        this.pack();
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public static void main(String []args) {

        blackKnight = new Knight("Black_Knight.png", "BLACK", "KNIGHT");
        blackKnight2 = new Knight("Black_Knight.png", "BLACK", "KNIGHT");

        new Main();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // This method will handle all the mouse clicks on the board.
        // Get the source Tile which is clicked.

        // Clear the board before every move.
        this.clearBoard();

        System.out.println(previousClick);

        Tile sourceTile = (Tile)(e.getSource());

        // Get its xAxis and yAxis.
        Integer xAxis = sourceTile.getXAxis(), yAxis = sourceTile.getYAxis();
        System.out.println(xAxis + " " + yAxis);

        // check if the tile has a piece.
        if (sourceTile.isTileOccupied()) {

            System.out.println("Tile occupied");

            // if the tile is occupied. get the piece from the tile.
            Piece currentPiece = sourceTile.getPiece();

            // get the type of piece.
            String type = currentPiece.getType();

            switch (type) {
                case "KNIGHT":
                    // cast the piece to knight.
                    Knight knight = (Knight)(currentPiece);
                    List <Move> possibleMovesKnight = knight.getAllPossibleMoves(this.tileBoard, xAxis, yAxis);
                    this.colorBoard(possibleMovesKnight);
                    break;
                case "ROOK":
                    Rook rook = (Rook)(currentPiece);
                    List <Move> possibleMovesRook = rook.getAllPossibleMoves(this.tileBoard, xAxis, yAxis);
                    this.colorBoard(possibleMovesRook);
                    break;
                default:
                    break;
            }

            previousTile = sourceTile;
            previousClick = currentPiece;


        } else {

            // When the tile is blank, check if previous click had a piece or not.
            if (previousClick != null) {

                List <Move> possibleMoves = previousClick.getPossibleMoves();
                for (Move move : possibleMoves) {
                    if (xAxis.equals(move.getX()) && yAxis.equals(move.getY())) {
                        // get coordinates of previous tiles.
                        int prevX = previousTile.getXAxis(), prevY = previousTile.getYAxis();
                        System.out.println(prevX + " " + prevY);
                        // remove the current tile.
                        this.tileBoard[prevX][prevY].removePiece();

                        // put that piece in the new position.
                        this.tileBoard[xAxis][yAxis].setPiece(previousClick);
                    }
                }

            } else {

                System.out.println("No piece earlier.");

            }

            previousTile = null;
            previousClick = null;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void colorBoard(List <Move> possibleMoves) {

        for (Move itr : possibleMoves) {
            // if the move is present. mark it as possible.
            int xAxis = itr.getX(), yAxis = itr.getY();

            this.tileBoard[xAxis][yAxis].setNextMove(true);
            this.tileBoard[xAxis][yAxis].repaint();

        }

    }

    private void clearBoard() {
        // Remove the color from all the tiles.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                this.tileBoard[i][j].setNextMove(false);
                this.tileBoard[i][j].repaint();
            }
        }
    }
}
