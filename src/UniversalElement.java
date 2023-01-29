import static java.lang.Math.sqrt;

public class UniversalElement {
    public static final double[][] shapeFunctionValuesFor2Points = new double[4][4];
    public static final double[][] shapeFunctionValuesFor3Points = new double[4][9];
    public static final double[][] shapeFunctionKsiDerivativeFor2Points = new double[4][4];
    public static final double[][] shapeFunctionEtaDerivativeFor2Points = new double[4][4];
    public static final double[][] shapeFunctionKsiDerivativeFor3Points = new double[9][4];
    public static final double[][] shapeFunctionEtaDerivativeFor3Points = new double[9][4];
    public static final double[] integralPointsKsiFor3Points = new double[9];
    public static final double[] integralPointsEtaFor3Points = new double[9];
    public static final double[] weights = new double[2];

    public static void init() {
        calculateShapeFunctionValuesFor2Points();
        calculateShapeFunctionValuesFor3Points();
        calculateShapeFunctionKsiDerivativeFor2Points();
        calculateShapeFunctionEtaDerivativeFor2Points();
        calculateShapeFunctionKsiDerivativeFor3Points();
        calculateShapeFunctionEtaDerivativeFor3Points();
        setIntegralPointsKsiFor3Points();
        setIntegralPointsEtaFor3Points();
        setWeights();
        }


    private static void calculateShapeFunctionValuesFor2Points() {
        shapeFunctionValuesFor2Points[0] = ShapeFunction.calculateN1for2Point();
        shapeFunctionValuesFor2Points[1] = ShapeFunction.calculateN2for2Point();
        shapeFunctionValuesFor2Points[2] = ShapeFunction.calculateN3for2Point();
        shapeFunctionValuesFor2Points[3] = ShapeFunction.calculateN4for2Point();
    }

    private static void calculateShapeFunctionValuesFor3Points() {
        shapeFunctionValuesFor3Points[0] = ShapeFunction.calculateN1For3Point();
        shapeFunctionValuesFor3Points[1] = ShapeFunction.calculateN2For3Point();
        shapeFunctionValuesFor3Points[2] = ShapeFunction.calculateN3For3Point();
        shapeFunctionValuesFor3Points[3] = ShapeFunction.calculateN4For3Point();
    }

    private static void calculateShapeFunctionKsiDerivativeFor2Points() {
        shapeFunctionKsiDerivativeFor2Points[0][0] = - 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[0][1] = 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[0][2] = 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[0][3] = - 0.25 * (1 + (- 1 / sqrt(3)));

        shapeFunctionKsiDerivativeFor2Points[1][0] = - 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[1][1] = 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[1][2] = 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[1][3] = - 0.25 * (1 + (- 1 / sqrt(3)));

        shapeFunctionKsiDerivativeFor2Points[2][0] = - 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[2][1] = 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[2][2] = 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[2][3] = - 0.25 * (1 + (1 / sqrt(3)));

        shapeFunctionKsiDerivativeFor2Points[3][0] = - 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[3][1] = 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[3][2] = 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionKsiDerivativeFor2Points[3][3] = - 0.25 * (1 + (1 / sqrt(3)));
    }

    private static void calculateShapeFunctionEtaDerivativeFor2Points() {
        shapeFunctionEtaDerivativeFor2Points[0][0] = - 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[0][1] = - 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[0][2] = 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[0][3] = 0.25 * (1 - (- 1 / sqrt(3)));

        shapeFunctionEtaDerivativeFor2Points[1][0] = - 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[1][1] = - 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[1][2] = 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[1][3] = 0.25 * (1 - (1 / sqrt(3)));

        shapeFunctionEtaDerivativeFor2Points[2][0] = - 0.25 * (1 - (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[2][1] = - 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[2][2] = 0.25 * (1 + (- 1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[2][3] = 0.25 * (1 - (- 1 / sqrt(3)));

        shapeFunctionEtaDerivativeFor2Points[3][0] = - 0.25 * (1 - (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[3][1] = - 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[3][2] = 0.25 * (1 + (1 / sqrt(3)));
        shapeFunctionEtaDerivativeFor2Points[3][3] = 0.25 * (1 - (1 / sqrt(3)));
    }

    private static void calculateShapeFunctionKsiDerivativeFor3Points() {
        shapeFunctionKsiDerivativeFor3Points[0][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[0][1] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[0][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[0][3] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));

        shapeFunctionKsiDerivativeFor3Points[1][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[1][1] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[1][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[1][3] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));

        shapeFunctionKsiDerivativeFor3Points[2][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[2][1] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[2][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[2][3] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));

        shapeFunctionKsiDerivativeFor3Points[3][0] = - 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[3][1] = 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[3][2] = 0.25 * (1 + (0));
        shapeFunctionKsiDerivativeFor3Points[3][3] = - 0.25 * (1 + (0));

        shapeFunctionKsiDerivativeFor3Points[4][0] = - 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[4][1] = 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[4][2] = 0.25 * (1 + (0));
        shapeFunctionKsiDerivativeFor3Points[4][3] = - 0.25 * (1 + (0));

        shapeFunctionKsiDerivativeFor3Points[5][0] = - 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[5][1] = 0.25 * (1 - (0));
        shapeFunctionKsiDerivativeFor3Points[5][2] = 0.25 * (1 + (0));
        shapeFunctionKsiDerivativeFor3Points[5][3] = - 0.25 * (1 + (0));

        shapeFunctionKsiDerivativeFor3Points[6][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[6][1] = 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[6][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[6][3] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));

        shapeFunctionKsiDerivativeFor3Points[7][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[7][1] = 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[7][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[7][3] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));

        shapeFunctionKsiDerivativeFor3Points[8][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[8][1] = 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[8][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionKsiDerivativeFor3Points[8][3] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));
    }

    private static void calculateShapeFunctionEtaDerivativeFor3Points() {
        shapeFunctionEtaDerivativeFor3Points[0][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[0][1] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[0][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[0][3] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));

        shapeFunctionEtaDerivativeFor3Points[1][0] = - 0.25 * (1 - (0));
        shapeFunctionEtaDerivativeFor3Points[1][1] = - 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[1][2] = 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[1][3] = 0.25 * (1 - (0));

        shapeFunctionEtaDerivativeFor3Points[2][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[2][1] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[2][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[2][3] = 0.25 * (1 - (sqrt(3.0 / 5.0)));

        shapeFunctionEtaDerivativeFor3Points[3][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[3][1] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[3][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[3][3] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));

        shapeFunctionEtaDerivativeFor3Points[4][0] = - 0.25 * (1 - (0));
        shapeFunctionEtaDerivativeFor3Points[4][1] = - 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[4][2] = 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[4][3] = 0.25 * (1 - (0));

        shapeFunctionEtaDerivativeFor3Points[5][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[5][1] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[5][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[5][3] = 0.25 * (1 - (sqrt(3.0 / 5.0)));

        shapeFunctionEtaDerivativeFor3Points[6][0] = - 0.25 * (1 - (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[6][1] = - 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[6][2] = 0.25 * (1 + (- sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[6][3] = 0.25 * (1 - (- sqrt(3.0 / 5.0)));

        shapeFunctionEtaDerivativeFor3Points[7][0] = - 0.25 * (1 - (0));
        shapeFunctionEtaDerivativeFor3Points[7][1] = - 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[7][2] = 0.25 * (1 + (0));
        shapeFunctionEtaDerivativeFor3Points[7][3] = 0.25 * (1 - (0));

        shapeFunctionEtaDerivativeFor3Points[8][0] = - 0.25 * (1 - (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[8][1] = - 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[8][2] = 0.25 * (1 + (sqrt(3.0 / 5.0)));
        shapeFunctionEtaDerivativeFor3Points[8][3] = 0.25 * (1 - (sqrt(3.0 / 5.0)));
    }

    private static void setWeights(){
        weights[0] = 8.0 / 9.0;
        weights[1] = 5.0 / 9.0;
    }

    private static void setIntegralPointsKsiFor3Points() {
        integralPointsKsiFor3Points[0] = - sqrt(3.0 / 5.0);
        integralPointsKsiFor3Points[1] = 0;
        integralPointsKsiFor3Points[2] = sqrt(3.0 / 5.0);
        integralPointsKsiFor3Points[3] = - sqrt(3.0 / 5.0);
        integralPointsKsiFor3Points[4] = 0;
        integralPointsKsiFor3Points[5] = sqrt(3.0 / 5.0);
        integralPointsKsiFor3Points[6] = - sqrt(3.0 / 5.0);
        integralPointsKsiFor3Points[7] = 0;
        integralPointsKsiFor3Points[8] = sqrt(3.0 / 5.0);
    }

    private static void setIntegralPointsEtaFor3Points() {
        integralPointsEtaFor3Points[0] = -sqrt(3.0 / 5.0);
        integralPointsEtaFor3Points[1] = -sqrt(3.0 / 5.0);
        integralPointsEtaFor3Points[2] = -sqrt(3.0 / 5.0);
        integralPointsEtaFor3Points[3] = 0;
        integralPointsEtaFor3Points[4] = 0;
        integralPointsEtaFor3Points[5] = 0;
        integralPointsEtaFor3Points[6] = sqrt(3.0 / 5.0);
        integralPointsEtaFor3Points[7] = sqrt(3.0 / 5.0);
        integralPointsEtaFor3Points[8] = sqrt(3.0 / 5.0);
    }

}
