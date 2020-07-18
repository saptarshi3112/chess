package Main;

import javax.swing.*;
import java.awt.*;

/**
 *
 * This class has the piece will be
 * extended by the pieces
 * of the game.
 *
 */

public class Tile extends JPanel {

    // Coordinates of the tile.
    private Integer xAxis;
    private Integer yAxis;

    private Piece piece;

    // This var will create the logo of the tile.
    private JLabel logo;

    // These params will decide the futhur color of the board.
    private boolean isInDanger = false;
    private boolean isNextMove = false;

    // Constructor.
    Tile(Integer xAxis, Integer yAxis, Piece piece) {

        this.xAxis = xAxis;
        this.yAxis = yAxis;

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        System.out.println(xAxis+ " " + yAxis);

        if ((xAxis+yAxis)%2 == 0) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(Color.GRAY);
        }

        this.setPiece(piece);

    }

    // this will set the image to the panel as a label.
    public void setPiece(Piece p) {
        if (p != null) {
            this.piece = p;
            ImageIcon img = new ImageIcon(this.getClass().getResource(p.getImagePath()));
            this.logo = new JLabel(img);
            this.logo.setHorizontalAlignment(JLabel.CENTER);
        } else {
            System.out.println("no piece");
            this.piece = null;
            this.logo = new JLabel();
        }

        // add the logo to the tile.
        this.add(logo);
    }

    public boolean isTileOccupied() {
        return this.getPiece() != null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Integer getXAxis() {
        return xAxis;
    }

    public void setXAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    public Integer getYAxis() {
        return yAxis;
    }

    public void setYAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    public JLabel getLogo() {
        return logo;
    }

    public void setLogo(JLabel logo) {
        this.logo = logo;
    }

    public void setNextMove(boolean nextMove) {
        this.isNextMove = nextMove;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // if it is next move, color yellow
        if (this.isNextMove) {
            this.setBackground(Color.YELLOW);
        } else {
            // else normal color.
            if ((this.xAxis + this.yAxis)%2 == 0) {
                this.setBackground(Color.WHITE);
            } else {
                this.setBackground(Color.GRAY);
            }
        }

    }

    void removePiece() {
        this.piece = null;
        this.logo.setIcon(null);
    }

}
