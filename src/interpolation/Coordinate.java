package interpolation;

import java.util.Scanner;

public class Coordinate {

    protected double x;
    protected double y;

    /* Default Constructor */
    public Coordinate() {
        /* Declare and Initialize Input Variable */
        Scanner input = new Scanner(System.in);
        System.out.print("Enter x: ");
        this.x = new Scanner(System.in).nextDouble();
        System.out.print("Enter y: ");
        this.y = new Scanner(System.in).nextDouble();
    }
    /* Overloaded Constructor with Paramters */
    public Coordinate(double x, double y) { this.x = x; this.y = y; }
    /* Getter-Setter Methods */
    public void setX() { this.x = x; }
    public void setY() { this.y = y; }
    @Override
    public String toString() { return "\n(" + this.x + "," + this.y + ")"; }
}
