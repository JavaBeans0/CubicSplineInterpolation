package interpolation;

import java.util.ArrayList;

public class XY_Tree {

    protected ArrayList<Coordinate> dataset;

    /* Overloaded Constructors */
    public XY_Tree() { dataset = new ArrayList<>(); }
    public XY_Tree(int coordinates) { setThis(coordinates); }
    public XY_Tree(ArrayList<Coordinate> dataset) { this.dataset = dataset; }
    /* Getter Setter Method */
    public void setDataset(ArrayList<Coordinate> dataset) { this.dataset= dataset; }
    public ArrayList<Coordinate> getDataset() { return this.dataset; }
    public int getTotalCoordinates () { return this.dataset.size(); }
    @Override
    public String toString() {
        String string = "";
        for(Coordinate coordinate: dataset)
            string += coordinate;

        return "\nXY-Tree: " + string;
    }
    /* Prompt User to Enter all Coordinates of the XY-Tree Dataset through iteration of the Coordinate Class Constructor Call */
    public void setThis(int coordinates) {
        this.dataset = new ArrayList<>(coordinates);
        for(int i = 0; i < coordinates; i++)
            this.dataset.add(new Coordinate());
    }

    /* Add new data point to dataset */
    public void set(double x, double y) { this.dataset.add(new Coordinate(x, y)); }

    /* Return the value of x at index i */
    public double getX(int index) { return this.dataset.get(index).x; }
    public double getY(int index) { return this.dataset.get(index).y; }
    /* Formula for Creating Spline */
}
