public class Grid {
    private int nodesNumber;
    private int elementsNumber;
    private Node[] nodes;
    private Element[] elements;

    public Grid(int nodesNumber, int elementsNumber) {
        this.nodesNumber = nodesNumber;
        this.elementsNumber = elementsNumber;
        this.nodes = new Node[nodesNumber];
        this.elements = new Element[elementsNumber];
    }


    public void calculateElements() {
        for(int i = 0; i < elementsNumber; i++) {
            elements[i].fillTables();
            elements[i].calculateHMatrix(this.nodes);
            elements[i].setSides(this.nodes);
            System.out.println("C MATRIX FOR ELEMENT" + i);
            elements[i].printCMatrix();
            elements[i].calculateHbcMatrix();
            elements[i].calculatePVector();
        }
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public void setNodesNumber(int nodesNumber) {
        this.nodesNumber = nodesNumber;
    }

    public int getElementsNumber() {
        return elementsNumber;
    }

    public void setElementsNumber(int elementsNumber) {
        this.elementsNumber = elementsNumber;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }
}
