public class Jacobian {

    private double matrix[][];
    private double detJ;

    public Jacobian(double matrix[][]) {
        this.matrix = matrix;
        this.detJ = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
    }

    public void printMatrix() {
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getDetJ() {
        return detJ;
    }

    public void setDetJ(double detJ) {
        this.detJ = detJ;
    }
}
