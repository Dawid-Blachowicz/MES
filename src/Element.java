import static java.lang.Math.sqrt;

public class Element {
    private int[] id;
    private final double[][] hMatrix;
    private final double[][] hbcMatrix;
    private final double[][] cMatrix;
    private final double[] pVector;
    private final double[][] derXN;
    private final double[][] derYN;
    private final Side[] sides;

    public Element(int[] id) {
        this.id = id;
        hMatrix = new double[4][4];
        hbcMatrix = new double[4][4];
        cMatrix = new  double[4][4];
        pVector = new double[4];
        derXN = new double[GlobalData.integralPointsNumber * GlobalData.integralPointsNumber][4];
        derYN = new double[GlobalData.integralPointsNumber * GlobalData.integralPointsNumber][4];
        sides = new Side[4];
    }

    private void calculateDerXN(Node[] nodes) {
        for(int i = 0 ; i < GlobalData.integralPointsNumber * GlobalData.integralPointsNumber; i++) {
            Jacobian jacobian =  new Jacobian(i, nodes, id);
            for(int j = 0; j < 4; j++) {
                if(GlobalData.integralPointsNumber == 2) {
                    derXN[i][j] = 1 / jacobian.getDetJ() * jacobian.getMatrix()[0][0] * UniversalElement.shapeFunctionKsiDerivativeFor2Points[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[0][1] * UniversalElement.shapeFunctionEtaDerivativeFor2Points[i][j];
                }
                else if(GlobalData.integralPointsNumber == 3) {
                    derXN[i][j] = 1 / jacobian.getDetJ() *  jacobian.getMatrix()[0][0] * UniversalElement.shapeFunctionKsiDerivativeFor3Points[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[0][1] * UniversalElement.shapeFunctionEtaDerivativeFor3Points[i][j];
                }
            }
        }
    }

    private void calculateDerYN(Node[] nodes) {
        for(int i = 0 ; i < GlobalData.integralPointsNumber * GlobalData.integralPointsNumber; i++) {
            Jacobian jacobian = new Jacobian(i, nodes, id);
            for(int j = 0; j < 4; j++) {
                if(GlobalData.integralPointsNumber == 2) {
                    derYN[i][j] = 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][0] * UniversalElement.shapeFunctionKsiDerivativeFor2Points[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][1] * UniversalElement.shapeFunctionEtaDerivativeFor2Points[i][j];
                }
                else if(GlobalData.integralPointsNumber == 3) {
                    derYN[i][j] = 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][0] * UniversalElement.shapeFunctionKsiDerivativeFor3Points[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][1] * UniversalElement.shapeFunctionEtaDerivativeFor3Points[i][j];
                }

            }
        }
    }

    public void calculateDerXNAndDerYN(Node[] nodes) {
        calculateDerXN(nodes);
        calculateDerYN(nodes);
    }

    private double[][] calculateHMatrixFor1Pc(int integralPoint, Node[] nodes, Jacobian jacobian) {
        double[][] matrix = new double[4][4];
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0; j < 4; j++) {
                matrix[i][j] = 25 *  jacobian.getDetJ() * (derXN[integralPoint][i] * derXN[integralPoint][j] + derYN[integralPoint][i] * derYN[integralPoint][j]);
            }
        }
        return multiplicationByWeights(integralPoint, matrix);
    }

    public void calculateHAndCMatrix(Node[] nodes) {
        calculateDerXNAndDerYN(nodes);
        double[][] matrixHTmp = new double[4][4];
        double[][] matrixCTmp = new double[4][4];
        for(int i = 0; i < GlobalData.integralPointsNumber * GlobalData.integralPointsNumber; i++) {
            Jacobian jacobian = new Jacobian(i, nodes, id);
            matrixHTmp = calculateHMatrixFor1Pc(i, nodes, jacobian);
            matrixCTmp = calculateCMatrixFor1Pc(jacobian.getDetJ(), i);
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    hMatrix[j][k] += matrixHTmp[j][k];
                    cMatrix[j][k] += matrixCTmp[j][k];
                }
            }
        }

        System.out.println();
    }

    public void printCMatrix() {

        System.out.println("-#############################################");
        for (double[] doubles : cMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
        System.out.println("-#############################################");
    }

    public double calculateSideLength(Node node1, Node node2)
    {
        return sqrt(Math.pow(node2.getX() - node1.getX(), 2) + Math.pow((node2.getY() - node1.getY()), 2));
    }

    public int[] getId() {
        return id;
    }

    public double[][] getHMatrix() {
        return hMatrix;
    }

    public double[][] getHbcMatrix() {
    return hbcMatrix;
    }

    public void setSides(Node[] nodes) {
        for(int i = 0; i < 4; i++) {
            sides[i] = new Side(i + 1, calculateSideLength(nodes[id[i] - 1], nodes[id[i + 1 == 4 ? 0 : i + 1] - 1]), (nodes[id[i] - 1].getBC() == 1 && nodes[id[i + 1 == 4 ? 0 : i + 1] - 1].getBC() == 1));
        }
    }

    public Side[] getSides() {
        return sides;
    }

    public void calculateHbcMatrixAndPVector() {
        for(int i = 0; i < 4; i++) {
            sides[i].calculateHbcMatrix();
            sides[i].calculatePVector();
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                hbcMatrix[i][j] =  sides[0].getHbcMatrix()[i][j] + sides[1].getHbcMatrix()[i][j] + sides[2].getHbcMatrix()[i][j] + sides[3].getHbcMatrix()[i][j] + hMatrix[i][j];
                pVector[i] = sides[0].getPVector()[i] + sides[1].getPVector()[i] + sides[2].getPVector()[i] + sides[3].getPVector()[i];
            }
        }

        System.out.println("LOCAL P VECOTOR");
        for (double doubles : pVector) {
            System.out.print(doubles + " ");
            System.out.println();
        }

    }

    public double[] getPVector() {
        return pVector;
    }

    public double[][] calculateCMatrixFor1Pc(double detJ, int integralPoint) {
        double[][] matrix = new double[4][4];
        if(GlobalData.integralPointsNumber == 2) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    matrix[i][j] = UniversalElement.shapeFunctionValuesFor2Points[i][integralPoint] * UniversalElement.shapeFunctionValuesFor2Points[j][integralPoint] * detJ * GlobalData.density * GlobalData.specificHeat;
                }
            }
        }
        else if(GlobalData.integralPointsNumber == 3) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    matrix[i][j] = UniversalElement.shapeFunctionValuesFor3Points[i][integralPoint] * UniversalElement.shapeFunctionValuesFor3Points[j][integralPoint] * detJ * GlobalData.density * GlobalData.specificHeat;
                }
            }
        }

        return multiplicationByWeights(integralPoint, matrix);
    }

    private double[][] multiplicationByWeights(int integralPoint, double[][] matrix) {
        if(GlobalData.integralPointsNumber == 3) {
            if(UniversalElement.integralPointsKsiFor3Points[integralPoint] == - sqrt(3.0 / 5.0) || UniversalElement.integralPointsKsiFor3Points[integralPoint] == sqrt(3.0 / 5.0)) {
                for(int i = 0 ; i < 4 ; i++) {
                    for (int j = 0; j < 4; j++) {
                        matrix[i][j] *= UniversalElement.weights[1];
                    }
                }
            }
            if(UniversalElement.integralPointsKsiFor3Points[integralPoint] == 0) {
                for(int i = 0 ; i < 4 ; i++) {
                    for (int j = 0; j < 4; j++) {
                        matrix[i][j] *= UniversalElement.weights[0];
                    }
                }
            }
            if(UniversalElement.integralPointsEtaFor3Points[integralPoint] == - sqrt(3.0 / 5.0) || UniversalElement.integralPointsEtaFor3Points[integralPoint] == sqrt(3.0 / 5.0)) {
                for(int i = 0 ; i < 4 ; i++) {
                    for (int j = 0; j < 4; j++) {
                        matrix[i][j] *= UniversalElement.weights[1];
                    }
                }
            }
            if(UniversalElement.integralPointsEtaFor3Points[integralPoint] == 0) {
                for(int i = 0 ; i < 4 ; i++) {
                    for (int j = 0; j < 4; j++) {
                        matrix[i][j] *= UniversalElement.weights[0];
                    }
                }
            }
        }

        return matrix;
    }

    public double[][] getCMatrix() {
        return cMatrix;
    }
}
