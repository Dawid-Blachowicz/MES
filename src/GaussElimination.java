import java.util.Arrays;

public class GaussElimination {

    public static double[] calculate(double[][] A, double[] b) {
        // Define the coefficient matrix A and the constant matrix b
        //double[][] A = {{2, 3, 1}, {1, 1, 2}, {3, 2, 3}};
        //double[] b = {5, 6, 7};

        // Perform Gauss elimination
        for (int i = 0; i < A.length - 1; i++) {
            // Find pivot element
            int pivot = i;
            for (int j = i + 1; j < A.length; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[pivot][i])) {
                    pivot = j;
                }
            }

            // Swap rows
            double[] temp = A[i];
            A[i] = A[pivot];
            A[pivot] = temp;
            double t = b[i];
            b[i] = b[pivot];
            b[pivot] = t;

            // Eliminate coefficients
            for (int j = i + 1; j < A.length; j++) {
                double factor = A[j][i] / A[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < A.length; k++) {
                    A[j][k] -= factor * A[i][k];
                }
            }
        }

        // Solve for variables
        double[] x = new double[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < A.length; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        return x;
    }
}