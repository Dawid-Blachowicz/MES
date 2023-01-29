public class CalculationManager {
    private final double[][] hgMatrix;
    private final double[][] hbcMatrix;
    private final double[] pVector;
    private final double[][] cMatrix;
    private double[] t;

    public CalculationManager(int numberOfNodes) {
        this.hgMatrix = new double[numberOfNodes][numberOfNodes];
        this.hbcMatrix = new double[numberOfNodes][numberOfNodes];
        this.pVector = new double[numberOfNodes];
        this.cMatrix = new double[numberOfNodes][numberOfNodes];
        this.t = new double[numberOfNodes];
    }

    public void calculateHgMatrix(Grid grid) {
        for(Element element : grid.getElements()) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4 ; j++) {
                    hgMatrix[element.getId()[i] - 1][element.getId()[j] - 1] += element.getHMatrix()[i][j];
                }
            }
        }
    }

    public void calculateHbcMatrix(Grid grid) {
        for(Element element : grid.getElements()) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4 ; j++) {
                    hbcMatrix[element.getId()[i] - 1][element.getId()[j] - 1] += element.getHbcMatrix()[i][j];
                }
            }
        }
    }

    public void calculatePVector(Grid grid) {
        for(Element element : grid.getElements()) {
            for(int i = 0; i < 4; i++) {
                pVector[element.getId()[i] - 1] += element.getPVector()[i];
            }
        }
    }

    public void calculateCMatrix(Grid grid) {
        for(Element element : grid.getElements()) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4 ; j++) {
                    cMatrix[element.getId()[i] - 1][element.getId()[j] - 1] += element.getCMatrix()[i][j];
                }
            }
        }
    }

    //TODO
    public double calculateTemperature(Grid grid, double initialTemp) {
        double temp = 0;
        return temp;
    }

    public void printHgMatrix() {
        System.out.println("HG MATRIX");
        for (double[] doubles : hgMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public void printHbcMatrix() {
        System.out.println("HBC MATRIX");
        for (double[] doubles : hbcMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public void printPVector() {
        System.out.println("P VECTOR");
        for(double doubles : pVector) {
            System.out.println(doubles);
        }
    }

    public void calculateT() {
        t = GaussElimination.calculate(hbcMatrix, pVector);
    }

    public void printCMatrix() {
        for (double[] doubles : cMatrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }
}
