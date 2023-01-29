import java.awt.*;

import static java.lang.Math.sqrt;

public class Side {
    private final int id;
    private final double[][] integralPoints;
    private final double[][] shapeFunctionValues;
    private final double[][] hbcMatrix;
    private final double length;
    private final double[] pVector;
    private final boolean BC;

    public Side(int id, double length, boolean BC) {
        this.id = id;
        integralPoints = new double[3][GlobalData.integralPointsNumber];
        setIntegralPoints();
        shapeFunctionValues = new double[GlobalData.integralPointsNumber][4];
        setShapeFunctionValues();
        hbcMatrix = new double[4][4];
        this.length = length;
        pVector = new double[4];
        this.BC = BC;
    }

    private void setShapeFunctionValues() {
            for(int i = 0; i < GlobalData.integralPointsNumber; i++) {
                shapeFunctionValues[i][0] = ShapeFunction.calculateN1(integralPoints[0][i], integralPoints[1][i]);
                shapeFunctionValues[i][1] = ShapeFunction.calculateN2(integralPoints[0][i], integralPoints[1][i]);
                shapeFunctionValues[i][2] = ShapeFunction.calculateN3(integralPoints[0][i], integralPoints[1][i]);
                shapeFunctionValues[i][3] = ShapeFunction.calculateN4(integralPoints[0][i], integralPoints[1][i]);
            }
    }

    private void setIntegralPoints() {
        if (GlobalData.integralPointsNumber == 2) {
            if (id == 1) {
                integralPoints[0][0] = -1 / sqrt(3);
                integralPoints[1][0] = -1;
                integralPoints[2][0] = 1;

                integralPoints[0][1] = 1 / sqrt(3);
                integralPoints[1][1] = -1;
                integralPoints[2][1] = 1;
            }
            if (id == 2) {
                integralPoints[0][0] = 1;
                integralPoints[1][0] = 1 / sqrt(3);
                integralPoints[2][0] = 1;

                integralPoints[0][1] = 1;
                integralPoints[1][1] = -1 / sqrt(3);
                integralPoints[2][1] = 1;
            }
            if (id == 3) {
                integralPoints[0][0] = -1 / sqrt(3);
                integralPoints[1][0] = 1;
                integralPoints[2][0] = 1;

                integralPoints[0][1] = 1 / sqrt(3);
                integralPoints[1][1] = 1;
                integralPoints[2][1] = 1;
            }
            if (id == 4) {
                integralPoints[0][0] = -1;
                integralPoints[1][0] = 1 / sqrt(3);
                integralPoints[2][0] = 1;

                integralPoints[0][1] = -1;
                integralPoints[1][1] = -1 / sqrt(3);
                integralPoints[2][1] = 1;
            }
        }
        else if (GlobalData.integralPointsNumber == 3) {
            if (id == 1) {
                integralPoints[0][0] = - sqrt(3.0 / 5.0);
                integralPoints[1][0] = -1;
                integralPoints[2][0] = 5.0 / 9.0;

                integralPoints[0][1] = 0;
                integralPoints[1][1] = -1;
                integralPoints[2][1] = 8.0 / 9.0;

                integralPoints[0][2] = sqrt(3.0 / 5.0);
                integralPoints[1][2] = -1;
                integralPoints[2][2] = 5.0 / 9.0;
            }
            if (id == 2) {
                integralPoints[0][0] = 1;
                integralPoints[1][0] = - sqrt(3.0 / 5.0);
                integralPoints[2][0] = 5.0 / 9.0;

                integralPoints[0][1] = 1;
                integralPoints[1][1] = 0;
                integralPoints[2][1] = 8.0 / 9.0;

                integralPoints[0][2] = 1;
                integralPoints[1][2] = sqrt(3.0 / 5.0);
                integralPoints[2][2] = 5.0 / 9.0;
            }
            if (id == 3) {
                integralPoints[0][0] = sqrt(3.0 / 5.0);
                integralPoints[1][0] = 1;
                integralPoints[2][0] = 5.0 / 9.0;

                integralPoints[0][1] = 0;
                integralPoints[1][1] = 1;
                integralPoints[2][1] = 8.0 / 9.0;

                integralPoints[0][2] = - sqrt(3.0 / 5.0);
                integralPoints[1][2] = 1;
                integralPoints[2][2] = 5.0 / 9.0;
            }
            if (id == 4) {
                integralPoints[0][0] = -1;
                integralPoints[1][0] = sqrt(3.0 / 5.0);
                integralPoints[2][0] = 5.0 / 9.0;

                integralPoints[0][1] = -1;
                integralPoints[1][1] = 0;
                integralPoints[2][1] = 8.0 / 9.0;

                integralPoints[0][2] = -1;
                integralPoints[1][2] = - sqrt(3.0 / 5.0);
                integralPoints[2][2] = 5.0 / 9.0;
            }
        }
    }

    public void calculateHbcMatrix(){
        for(int i = 0 ; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(BC) {
                    if(GlobalData.integralPointsNumber == 2){
                        hbcMatrix[i][j] = GlobalData.alfa * (integralPoints[2][0] * shapeFunctionValues[0][i] * shapeFunctionValues[0][j] + integralPoints[2][1] * shapeFunctionValues[1][i] * shapeFunctionValues[1][j]) * length / 2;
                    }
                    else if(GlobalData.integralPointsNumber == 3) {
                        hbcMatrix[i][j] = GlobalData.alfa * (integralPoints[2][0] * shapeFunctionValues[0][i] * shapeFunctionValues[0][j] + integralPoints[2][1] * shapeFunctionValues[1][i] * shapeFunctionValues[1][j] + integralPoints[2][2] * shapeFunctionValues[2][i] * shapeFunctionValues[2][j]) * length/2;
                    }
                }
                else {
                    hbcMatrix[i][j] = 0.0;
                }
            }
        }
    }

    public void printHbcMatrix() {
        for (double[] doubles : hbcMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public void calculatePVector() {
        for(int i = 0; i < 4; i++) {
            if(BC) {
                if(GlobalData.integralPointsNumber == 2) {
                    pVector[i] = GlobalData.alfa * (shapeFunctionValues[0][i] * integralPoints[2][0] + shapeFunctionValues[1][i] * integralPoints[2][1]) * GlobalData.tot * length / 2;
                }
                else if(GlobalData.integralPointsNumber == 3) {
                    pVector[i] = GlobalData.alfa * (shapeFunctionValues[0][i] * integralPoints[2][0] + shapeFunctionValues[1][i] * integralPoints[2][1] + shapeFunctionValues[2][i] * integralPoints[2][2]) * GlobalData.tot * length / 2;
                }
            }
            else {
                pVector[i] = 0.0;
            }
        }
    }

    public double[][] getHbcMatrix() {
        return hbcMatrix;
    }

    public double[] getPVector() {
        return pVector;
    }
}
