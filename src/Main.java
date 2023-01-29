import java.io.*;

public class Main {

    public static void main(String args[]) throws IOException {

        int nodesNumber;
        int elementsNumber;
        UniversalElement.init();

        BufferedReader bufferedReader = null;
        try {
            //bufferedReader = new BufferedReader(new FileReader("Test1_4_4.txt"));
            bufferedReader = new BufferedReader(new FileReader("Test2_4_4_MixGrid.txt"));
            //bufferedReader = new BufferedReader(new FileReader("Test3_31_31_kwadrat.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " " + e.getCause());
            return;
        }

        String line;
        String[] arrOfString;

        //try {
            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.simulationTime = Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.simulationStepTime = Integer.parseInt(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.conductivity =  Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.alfa = Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.tot = Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.initialTemp = Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.density = Double.parseDouble(arrOfString[1]);


            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            GlobalData.specificHeat = Double.parseDouble(arrOfString[1]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            nodesNumber = Integer.parseInt(arrOfString[2]);

            line = bufferedReader.readLine();
            arrOfString = line.split(" ");
            elementsNumber = Integer.parseInt(arrOfString[2]);

            line = bufferedReader.readLine();

            Grid grid = new Grid(nodesNumber, elementsNumber);
            for(int i = 0; i < grid.getNodesNumber(); i++) {
                line = bufferedReader.readLine();
                String trimedLine = line.replaceAll("\\s","");
                arrOfString = trimedLine.split(",");
                grid.getNodes()[i] = new Node(Double.parseDouble(arrOfString[1]), Double.parseDouble(arrOfString[2]));
                //grid.getNodes()[i].setX(Double.parseDouble(arrOfString[1]));
                //grid.getNodes()[i].setY(Double.parseDouble(arrOfString[2]));
            }

            line = bufferedReader.readLine();

            for(int i = 0; i < grid.getElementsNumber(); i++) {
                line = bufferedReader.readLine();
                String trimedLine = line.replaceAll("\\s","");
                arrOfString = trimedLine.split(",");
                int[] id = new int[4];
                for(int j = 0; j < 4; j++) {
                    id[j] = Integer.parseInt(arrOfString[j + 1]);
                }
                grid.getElements()[i] = new Element(id);
            }

            line = bufferedReader.readLine();

            line = bufferedReader.readLine();
            arrOfString = line.split(", ");
        for (String s : arrOfString) {
            grid.getNodes()[Integer.parseInt(s) - 1].setBC(1);
        }


        for(Node node : grid.getNodes()) {
            node.printCoordinates();
            System.out.println();
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();

        grid.calculateElements();
        CalculationManager calculationManager = new CalculationManager(nodesNumber);
        calculationManager.calculateHgMatrix(grid);
        calculationManager.printHgMatrix();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();

        System.out.println("hbc matrix");
        grid.getElements()[1].getSides()[0].printHbcMatrix();
        System.out.println();
        grid.getElements()[2].getSides()[1].printHbcMatrix();
        System.out.println();
        grid.getElements()[3].getSides()[2].printHbcMatrix();
        System.out.println();
        grid.getElements()[4].getSides()[3].printHbcMatrix();

        System.out.println();
        System.out.println();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();

        calculationManager.calculateHbcMatrix(grid);
        calculationManager.printHbcMatrix();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();

        calculationManager.calculatePVector(grid);
        calculationManager.printPVector();

        System.out.println();
        System.out.println("------------DOOOOOOOOOOOOOOOOOOPA---------------------------------------");
        System.out.println();

        calculationManager.calculateCMatrix(grid);
        calculationManager.printCMatrix();

        System.out.println();
        System.out.println("--------------------------------DOOOOOOOOOOOPA-------------------------");
        System.out.println();


        System.out.println("Temperatury w kolejnych krokach");
        double temp = calculationManager.calculateTemperature(grid, GlobalData.initialTemp);
        for(int i = GlobalData.simulationStepTime; i <= GlobalData.simulationTime; i += GlobalData.simulationStepTime) {
            calculationManager.calculateHbcMatrix(grid);
            calculationManager.calculateCMatrix(grid);
            calculationManager.calculatePVector(grid);
            System.out.println(temp);
            temp = calculationManager.calculateTemperature(grid, temp);
        }





    }
}
