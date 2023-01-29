public class Jacobian {

    private double[][] matrix;
    private double detJ;

    public Jacobian(int integralPoint, Node[] nodes, int[] id) {
        matrix =  new double[2][2];
        calculateMatrix(integralPoint, nodes, id);
        this.detJ = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
    }

    private void calculateMatrix(int integralPoint, Node[] nodes, int[] id) {
        double result = 0.0;
        //double[][] matrix = new double[2][2];
        if(GlobalData.integralPointsNumber == 2) {
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionKsiDerivativeFor2Points[integralPoint][i] * nodes[id[i] - 1].getX();
            }
            matrix[0][0] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionKsiDerivativeFor2Points[integralPoint][i] * nodes[id[i] - 1].getY();
            }
            matrix[0][1] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionEtaDerivativeFor2Points[integralPoint][i] * nodes[id[i] - 1].getX();
            }
            matrix[1][0] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionEtaDerivativeFor2Points[integralPoint][i] * nodes[id[i] - 1].getY();
            }
            matrix[1][1] = result;
        }
        else if(GlobalData.integralPointsNumber == 3) {
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionKsiDerivativeFor3Points[integralPoint][i] * nodes[id[i] - 1].getX();
            }
            matrix[0][0] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionKsiDerivativeFor3Points[integralPoint][i] * nodes[id[i] - 1].getY();
            }
            matrix[0][1] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionEtaDerivativeFor3Points[integralPoint][i] * nodes[id[i] - 1].getX();
            }
            matrix[1][0] = result;

            result = 0;
            for (int i = 0; i < 4; i++) {
                result += UniversalElement.shapeFunctionEtaDerivativeFor3Points[integralPoint][i] * nodes[id[i] - 1].getY();
            }
            matrix[1][1] = result;
        }
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
