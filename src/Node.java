public class Node {
    private double x;
    private double y;
    private int BC;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
        this.BC = 0;
    }

    public void printCoordinates() {
        System.out.printf(x + ", " + y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getBC() {
        return BC;
    }

    public void setBC(int BC) {
        this.BC = BC;
    }
}
