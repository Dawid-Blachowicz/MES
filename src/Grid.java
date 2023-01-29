public class Grid {
    private final int nodesNumber;
    private final int elementsNumber;
    private final Node[] nodes;
    private final Element[] elements;

    public Grid(int nodesNumber, int elementsNumber) {
        this.nodesNumber = nodesNumber;
        this.elementsNumber = elementsNumber;
        this.nodes = new Node[nodesNumber];
        this.elements = new Element[elementsNumber];
    }


    public void calculateElements() {
        for(int i = 0; i < elementsNumber; i++) {
            elements[i].calculateHAndCMatrix(this.nodes);
            elements[i].setSides(this.nodes);
            System.out.println("C MATRIX FOR ELEMENT" + i);
            elements[i].printCMatrix();
            elements[i].calculateHbcMatrixAndPVector();
        }
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public int getElementsNumber() {
        return elementsNumber;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Element[] getElements() {
        return elements;
    }
}
