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

    private static Knight blackKnight, blackKnight2, whiteKnight, whiteKnight2;
    private static Rook blackRook, blackRook2, whiteRook, whiteRook2;
    private static Bishop blackBishop, blackBishop2;

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

                // The first row will contain all the black main pieces.
                if (i == 0) {
                    if (j == 0) {
                        tempPiece = blackRook;
                    } if (j == 1) {
                        tempPiece = blackKnight;
                    } if (j == 2) {
                        tempPiece = blackBishop;
                    } if (j == 5) {
                        tempPiece = blackBishop2;
                    } if (j == 6) {
                        tempPiece = blackKnight2;
                    } if (j == 7) {
                        tempPiece = blackRook2;
                    }
                }

                // the last row will contain all the white main ones.
                if (i == 7) {
                    if (j == 0) {
                        tempPiece = whiteRook;
                    } if (j == 1) {
                        tempPiece = whiteKnight;
                    } if (j == 6) {
                        tempPiece = whiteKnight2;
                    } if (j == 7) {
                        tempPiece = whiteRook2;
                    }
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
        whiteKnight = new Knight("White_Knight.png", "WHITE", "KNIGHT");
        whiteKnight2 = new Knight("White_Knight.png", "WHITE", "KNIGHT");

        blackRook = new Rook("Black_Rook.png", "BLACK", "ROOK");
        blackRook2 = new Rook("Black_Rook.png", "BLACK", "ROOK");
        whiteRook = new Rook("White_Rook.png", "WHITE", "ROOK");
        whiteRook2 = new Rook("White_Rook.png", "WHITE", "ROOK");

        blackBishop = new Bishop("Black_Bishop.png", "BLACK", "BISHOP");
        blackBishop2 = new Bishop("Black_Bishop.png", "BLACK", "BISHOP");

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
            System.out.println(type);

            // This block will take care of the coloring of the board.
            switch (type) {
                case "KNIGHT":
                    try {
                        // cast the piece to knight.
                        Knight knight = (Knight) (currentPiece);
                        List<Move> possibleMovesKnight = knight.getAllPossibleMoves(this.tileBoard, xAxis, yAxis);
                        this.colorBoard(possibleMovesKnight);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "ROOK":
                    try {
                        Rook rook = (Rook) (currentPiece);
                        List<Move> possibleMovesRook = rook.getAllPossibleMoves(this.tileBoard, xAxis, yAxis);
                        this.colorBoard(possibleMovesRook);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "BISHOP":

                    try {

                        Bishop bishop = (Bishop) (currentPiece);
                        List<Move> possibleMovesBishop = bishop.getAllPossibleMoves(this.tileBoard, xAxis, yAxis);
                        this.colorBoard(possibleMovesBishop);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    break;

                default:
                    System.out.println("No piece of this type.");
                    break;
            }

            previousTile = sourceTile;
            previousClick = currentPiece;


        } else {

            // When the tile is blank, check if previous click had a piece or not.
            if (previousClick != null) {

                // if that click is in the list of possible moves.
                // We can move a piece there.
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
                    } else {
                        System.out.println("Invalid move");
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
        for (Move move : possibleMoves) {
            // if the move is present. mark it as possible.
            System.out.println(move.getX() + " " + move.getY() + " " + move.isHasEnemy() + " " + move.isValidMove());
            int xAxis = move.getX(), yAxis = move.getY();

            boolean isAttackMove = move.isHasEnemy();
            boolean isNextMove = move.isValidMove();

            this.tileBoard[xAxis][yAxis].setNextMove(isNextMove);
            this.tileBoard[xAxis][yAxis].setInDanger(isAttackMove);
            this.tileBoard[xAxis][yAxis].repaint();
        }
    }

    private void clearBoard() {
        // Remove the color from all the tiles.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                this.tileBoard[i][j].setNextMove(false);
                this.tileBoard[i][j].setInDanger(false);
                this.tileBoard[i][j].repaint();
            }
        }
    }
}
