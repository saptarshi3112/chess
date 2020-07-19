package Main;

public class Move {
    private Integer x;
    private Integer y;

    private boolean hasEnemy;
    private boolean isValidMove;

    Move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    Move (Integer x, Integer y, boolean hasEnemy, boolean isValidMove) {
        this.x = x;
        this.y = y;
        this.hasEnemy = hasEnemy;
        this.isValidMove = isValidMove;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isHasEnemy() {
        return hasEnemy;
    }

    public void setHasEnemy(boolean hasEnemy) {
        this.hasEnemy = hasEnemy;
    }

    public boolean isValidMove() {
        return isValidMove;
    }

    public void setValidMove(boolean validMove) {
        isValidMove = validMove;
    }


}
