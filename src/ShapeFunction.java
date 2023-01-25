import static java.lang.Math.sqrt;

public class ShapeFunction {
    public static double calculateN1(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 - eta);
    }

    public static double calculateN2(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 - eta);
    }

    public static double calculateN3(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 + eta);
    }

    public static double calculateN4(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 + eta);
    }

    public static double[] calculateN1for2Point() {
        double[] N = new double[4];
        N[0] = calculateN1(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[1] = calculateN1(1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[2] = calculateN1(- 1.0 / sqrt(3), 1.0 / sqrt(3));
        N[3] = calculateN1(1.0 / sqrt(3), 1.0 / sqrt(3));

        return N;
    }

    public static double[] calculateN2for2Point() {
        double[] N = new double[4];
        N[0] = calculateN2(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[1] = calculateN2(1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[2] = calculateN2(- 1.0 / sqrt(3), 1.0 / sqrt(3));
        N[3] = calculateN2(1.0 / sqrt(3), 1.0 / sqrt(3));

        return N;
    }

    public static double[] calculateN3for2Point() {
        double[] N = new double[4];
        N[0] = calculateN3(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[1] = calculateN3(1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[2] = calculateN3(- 1.0 / sqrt(3), 1.0 / sqrt(3));
        N[3] = calculateN3(1.0 / sqrt(3), 1.0 / sqrt(3));

        return N;
    }

    public static double[] calculateN4for2Point() {
        double[] N = new double[4];
        N[0] = calculateN4(- 1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[1] = calculateN4(1.0 / sqrt(3), - 1.0 / sqrt(3));
        N[2] = calculateN4(- 1.0 / sqrt(3), 1.0 / sqrt(3));
        N[3] = calculateN4(1.0 / sqrt(3), 1.0 / sqrt(3));

        return N;
    }

    public static double[] calculateN1For3Point() {
        double[] N = new double[9];
        N[0] = calculateN1(- sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[1] = calculateN1(0, - sqrt(3.0/5.0));
        N[2] = calculateN1(sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[3] = calculateN1(- sqrt(3.0/5.0), 0);
        N[4] = calculateN1(0, 0);
        N[5] = calculateN1(sqrt(3.0/5.0), 0);
        N[6] = calculateN1(- sqrt(3.0/5.0), sqrt(3.0/5.0));
        N[7] = calculateN1(0, sqrt(3.0/5.0));
        N[8] = calculateN1(sqrt(3.0/5.0), sqrt(3.0/5.0));

        return N;
    }

    public static double[] calculateN2For3Point() {
        double[] N = new double[9];
        N[0] = calculateN2(- sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[1] = calculateN2(0, - sqrt(3.0/5.0));
        N[2] = calculateN2(sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[3] = calculateN2(- sqrt(3.0/5.0), 0);
        N[4] = calculateN2(0, 0);
        N[5] = calculateN2(sqrt(3.0/5.0), 0);
        N[6] = calculateN2(- sqrt(3.0/5.0), sqrt(3.0/5.0));
        N[7] = calculateN2(0, sqrt(3.0/5.0));
        N[8] = calculateN2(sqrt(3.0/5.0), sqrt(3.0/5.0));

        return N;
    }

    public static double[] calculateN3For3Point() {
        double[] N = new double[9];
        N[0] = calculateN3(- sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[1] = calculateN3(0, - sqrt(3.0/5.0));
        N[2] = calculateN3(sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[3] = calculateN3(- sqrt(3.0/5.0), 0);
        N[4] = calculateN3(0, 0);
        N[5] = calculateN3(sqrt(3.0/5.0), 0);
        N[6] = calculateN3(- sqrt(3.0/5.0), sqrt(3.0/5.0));
        N[7] = calculateN3(0, sqrt(3.0/5.0));
        N[8] = calculateN3(sqrt(3.0/5.0), sqrt(3.0/5.0));

        return N;
    }

    public static double[] calculateN4For3Point() {
        double[] N = new double[9];
        N[0] = calculateN4(- sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[1] = calculateN4(0, - sqrt(3.0/5.0));
        N[2] = calculateN4(sqrt(3.0/5.0), - sqrt(3.0/5.0));
        N[3] = calculateN4(- sqrt(3.0/5.0), 0);
        N[4] = calculateN4(0, 0);
        N[5] = calculateN4(sqrt(3.0/5.0), 0);
        N[6] = calculateN4(- sqrt(3.0/5.0), sqrt(3.0/5.0));
        N[7] = calculateN4(0, sqrt(3.0/5.0));
        N[8] = calculateN4(sqrt(3.0/5.0), sqrt(3.0/5.0));

        return N;
    }

}
