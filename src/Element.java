import static java.lang.Math.sqrt;

public class Element {
    private int[] id;
    private final double[][] hMatrix;
    private final double[][] hbcMatrix;
    private final double[][] cMatrix;
    private final double[] pVector;
    private int tHeight;
    private final int integralPointsNumber;
    private int shapeFunNumber;
    private final double[][] tEta;
    private final double[][] tKsi;
    private final double[][] derXN;
    private final double[][] derYN;
    private final Side[] sides;

    public Element(int integralPointsNumber) {
        this.integralPointsNumber = integralPointsNumber;
        tHeight = integralPointsNumber * integralPointsNumber;
        shapeFunNumber = 4;
        tEta = new double[this.tHeight][shapeFunNumber];
        tKsi = new double[this.tHeight][shapeFunNumber];
        derXN = new double[this.tHeight][shapeFunNumber];
        derYN = new double[this.tHeight][shapeFunNumber];
        hMatrix = new double[4][4];
        hbcMatrix = new double[4][4];
        cMatrix = new  double[4][4];
        pVector = new double[4];
        sides = new Side[4];
    }

    public Element(int[] id) {
        this.id = id;
        hMatrix = new double[4][4];
        hbcMatrix = new double[4][4];
        cMatrix = new  double[4][4];
        pVector = new double[4];
        integralPointsNumber = 2;
        tHeight = integralPointsNumber * integralPointsNumber;
        shapeFunNumber = 4;
        tEta = new double[this.tHeight][shapeFunNumber];
        tKsi = new double[this.tHeight][shapeFunNumber];
        derXN = new double[this.tHeight][shapeFunNumber];
        derYN = new double[this.tHeight][shapeFunNumber];
        sides = new Side[4];
    }



    public void printTables() {
        printTKsi();
        System.out.println();
        printTEta();
    }

    public void printTEta() {
        for (double[] doubles : tEta) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public void printTKsi() {
        for (double[] doubles : tKsi) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public void fillTables() {
        if(integralPointsNumber == 2) {
            fillTEtaFor2Points();
            fillTKsiFor2Points();
        }
        else {
            fillTEtaFor3Points();
            fillTKsiFor3Points();
        }

    }

    private void fillTEtaFor2Points() {
        double ksi = 1 / sqrt(3) * (-1);
        for (int i = 0; i < tEta.length ; i++) {
            for (int j = 0; j < tEta[i].length; j++) {
                tEta[i][j] = calculateEtaDerivative(j + 1, ksi);
            }
            ksi *= (-1);
        }
    }

    private void fillTKsiFor2Points() {
        double eta = 1 / sqrt(3) * (-1);
        for (int i = 0; i < tKsi.length; i++) {
            if(i > 1 && eta < 0) {
                eta *= -1;
            }
            for (int j = 0; j < tKsi[i].length; j++) {
                tKsi[i][j] = calculateKsiDerivative(j + 1, eta);
            }
        }
    }

    private void fillTEtaFor3Points() {
        double ksi = sqrt(3.0 / 5.0) * (-1);
        int counter = 1;
        for (int i = 0; i < tEta.length; i++) {
            if(counter > 3) {
                ksi = sqrt(3.0 / 5.0) * (-1);
                counter = 1;
            }
            if(counter == 2) {
                ksi = 0;
            }
            else if(counter == 3) {
                ksi = sqrt(3.0 / 5.0);
            }
            for (int j = 0; j < tEta[i].length; j++) {
                tEta[i][j] = calculateEtaDerivative(j + 1, ksi);
            }
            counter++;
        }
    }

    private void fillTKsiFor3Points() {
        double eta = sqrt(3.0 / 5.0) * (-1);
        int counter = 1;
        for (int i = 0; i < tKsi.length; i++ ) {
            if(counter > 3 && counter < 7) {
                eta = 0;
            }
            else if (counter > 6) {
                eta = sqrt(3.0 / 5.0) * (-1);
            }
            for (int j = 0; j < tKsi[i].length; j++) {
                tKsi[i][j] = calculateKsiDerivative(j + 1, eta);
            }
            counter++;
        }
    }

    private double calculateEtaDerivative(int n, double ksi) {
        if (n == 1) {
            return 0.25 * (1 - ksi) * (-1);
        }
        else if (n == 2) {
            return 0.25 * (1 + ksi) * (- 1);
        }
        else if (n == 3) {
            return 0.25 * (1 + ksi);
        }
        else {
            return 0.25 * (1- ksi);
        }
    }

    private double calculateKsiDerivative(int n, double eta) {
        if (n == 1) {
            return 0.25 * (1 - eta) * (-1);
        }
        else if (n == 2) {
            return 0.25 * (1 - eta);
        }
        else if (n == 3) {
            return 0.25 * (1 + eta);
        }
        else {
            return 0.25 * (1 + eta) * (-1);
        }
    }

    private Jacobian calculateJacobian(int integralPoint, Node[] nodes) {
        double result = 0.0;
        double[][] matrix = new double[2][2];
        for(int i = 0; i < 4; i++) {
            result += tKsi[integralPoint][i] * nodes[id[i] - 1].getX();
        }
        matrix[0][0] = result;

        result = 0;
        for(int i = 0 ; i < 4 ; i++) {
            result += tKsi[integralPoint][i] * nodes[id[i] - 1].getY();
        }
        matrix[0][1] = result;

        result = 0;
        for(int i = 0 ; i < 4 ; i++) {
            result += tEta[integralPoint][i] * nodes[id[i] - 1].getX();
        }
        matrix[1][0] = result;

        result = 0;
        for(int i = 0 ; i < 4 ; i++) {
            result += tEta[integralPoint][i] * nodes[id[i] - 1].getY();
        }
        matrix[1][1] = result;

        return new Jacobian(matrix);
    }

    private void calculateDerXN(Node[] nodes) {
        Jacobian jacobian;
        for(int i = 0 ; i < tHeight; i++) {
            jacobian = calculateJacobian(i, nodes);
            for(int j = 0; j < 4; j++) {
                derXN[i][j] = 1 / jacobian.getDetJ() *  jacobian.getMatrix()[0][0] * tKsi[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[0][1] * tEta[i][j];
            }
        }
    }

    private void calculateDerYN(Node[] nodes) {
        Jacobian jacobian;
        for(int i = 0 ; i < tHeight; i++) {
            jacobian = calculateJacobian(i, nodes);
            for(int j = 0; j < 4; j++) {
                derYN[i][j] = 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][0] * tKsi[i][j] + 1 / jacobian.getDetJ() * jacobian.getMatrix()[1][1] * tEta[i][j];
            }
        }
    }

    public void calculateDerXNAndDerYN(Node[] nodes) {
        calculateDerXN(nodes);
        calculateDerYN(nodes);
    }

    public void printDerXY() {
        for (double[] doubles : derXN) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------");
        System.out.println();

        for (double[] doubles : derYN) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    private double[][] calculateHMatrixFor1Pc(int integralPoint, Node[] nodes) {
        double[][] derX = new double[4][4];
        double[][] derY = new double[4][4];
        double[][] matrix = new double[4][4];
        Jacobian jacobian = calculateJacobian(integralPoint, nodes);
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0; j < 4; j++) {
                derX[i][j] = derXN[integralPoint][i] * derXN[integralPoint][j];
                derY[i][j] = derYN[integralPoint][i] * derYN[integralPoint][j];
                matrix[i][j] = 25 *  jacobian.getDetJ() * (derX[i][j] + derY[i][j]);
            }
        }

        return matrix;
    }

    public void calculateHMatrix(Node[] nodes) {
        calculateDerXNAndDerYN(nodes);
        double[][] matrixHTmp = new double[4][4];
        double[][] matrixCTmp = new double[4][4];
        for(int i = 0; i < 4; i++) {
            matrixHTmp = calculateHMatrixFor1Pc(i, nodes);
            matrixCTmp = calculateCMatrix(calculateJacobian(i, nodes).getDetJ(), i);
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    hMatrix[j][k] += matrixHTmp[j][k];
                    cMatrix[j][k] += matrixCTmp[j][k];
                }
            }
        }
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

    public void setId(int[] id) {
        this.id = id;
    }

    public double[][] getHMatrix() {
        return hMatrix;
    }

    public double[][] getHbcMatrix() {
    return hbcMatrix;
    }

    public void setSides(Node[] nodes) {
        System.out.println(id[0]);
        System.out.println(id[1]);
        System.out.println(id[2]);
        System.out.println(id[3]);
        System.out.println();
        for(int i = 0; i < 4; i++) {
            System.out.println(i + " " +id[i + 1 == 4 ? 0 : i + 1]);
            sides[i] = new Side(i + 1, integralPointsNumber, calculateSideLength(nodes[id[i] - 1], nodes[id[i + 1 == 4 ? 0 : i + 1] - 1]), (nodes[id[i] - 1].getBC() == 1 && nodes[id[i + 1 == 4 ? 0 : i + 1] - 1].getBC() == 1));

        }
    }

    public Side[] getSides() {
        return sides;
    }

    public void calculateHbcMatrix() {

        for(int i = 0; i < 4; i++) {
            sides[i].calculateHbcMatrix(25);
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                hbcMatrix[i][j] =  sides[0].getHbcMatrix()[i][j] + sides[1].getHbcMatrix()[i][j] + sides[2].getHbcMatrix()[i][j] + sides[3].getHbcMatrix()[i][j] + hMatrix[i][j];
            }
        }
    }

    public void printHbcMatrix() {
        System.out.println("-##### HBC MATRIX");
        for (double[] doubles : hbcMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
        System.out.println("-##### HBC MATRIX");

    }

    public void calculatePVector() {
        for(int i = 0; i < 4; i++) {
            sides[i].calculatePVector(1200, 300);
        }

        for(int i = 0; i < 4; i++) {
            pVector[i] = sides[0].getPVector()[i] + sides[1].getPVector()[i] + sides[2].getPVector()[i] + sides[3].getPVector()[i];
        }

        System.out.println("-##### P VECTOR");
        for (double doubles : pVector) {
                System.out.print(doubles + " ");
            System.out.println();
        }
    }

    public double[] getPVector() {
        return pVector;
    }

    public double[][] calculateCMatrix(double detJ, int integralPoint) {
        double w1 = 1.0;
        double w2 = 1.0;
        double[][] matrix = new double[4][4];
        double[] shapeFunctionValues =  new double[4];
        if(integralPoint == 0) {
            shapeFunctionValues[0] = ShapeFunction.calculateN1(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[1] = ShapeFunction.calculateN2(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[2] = ShapeFunction.calculateN3(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[3] = ShapeFunction.calculateN4(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
        }
        else if(integralPoint == 1) {
            shapeFunctionValues[0] = ShapeFunction.calculateN1(1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[1] = ShapeFunction.calculateN2(1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[2] = ShapeFunction.calculateN3(1.0 / sqrt(3), - 1.0 / sqrt(3));
            shapeFunctionValues[3] = ShapeFunction.calculateN4(1.0 / sqrt(3), - 1.0 / sqrt(3));
        }
        else if(integralPoint == 2) {
            shapeFunctionValues[0] = ShapeFunction.calculateN1(1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[1] = ShapeFunction.calculateN2(1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[2] = ShapeFunction.calculateN3(1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[3] = ShapeFunction.calculateN4(1.0 / sqrt(3), 1.0 / sqrt(3));
        }
        else if(integralPoint == 3) {
            shapeFunctionValues[0] = ShapeFunction.calculateN1(- 1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[1] = ShapeFunction.calculateN2(- 1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[2] = ShapeFunction.calculateN3(- 1.0 / sqrt(3), 1.0 / sqrt(3));
            shapeFunctionValues[3] = ShapeFunction.calculateN4(- 1.0 / sqrt(3), 1.0 / sqrt(3));
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                matrix[i][j] = shapeFunctionValues[i] * w1 * shapeFunctionValues[j] * w2 * detJ * GlobalData.density * GlobalData.specificHeat;
            }
        }

        return matrix;
    }

    public double[][] getCMatrix() {
        return cMatrix;
    }
}
