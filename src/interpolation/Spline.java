package interpolation;

import java.util.ArrayList;
import java.util.Scanner;

import java.text.DecimalFormat;

import java.lang.Math;

public class Spline extends XY_Tree {
    /* Private Fields */
    private int index;
    private double value;
    /* Default Constructor */
    public Spline() { super(); }
    /* Overloaded Constructors */
    public Spline(int coordinates) { super(coordinates); }
    /* Direct Constructor */
    public Spline(ArrayList<Coordinate> dataset) { super(dataset); }

    /* Important Method for Creating Dataset from FileReader: Custom File Read Functionality */
    public void set(double x, double y) {super.set(x, y); }
    /* Getter Setter Methods */
    public void setLvl(int index) { this.index = index; }
    public int getLvl() { return this.index; }

    /* Display formula for either lvl 0 or 1 of spline --- Still in under Construction */
    public String S_of(boolean l) {
        String string = "";
        for(int i = 1; i < 4; i++)
            string += (" + " + Character.toString('a' + i) + (l?0:1) + "(" + super.getX(this.index+1) + "-" + Double.toString(super.getX(this.index + (l?0:1))) + ")" + (i == 1 ? "": "^" + i));

        return "S" + (l?0:1)  + "(x1) = a" + (l?0:1)  + string;
    }

    /* Display formula for either lvl 0 or 1 of spline */
    public String S(boolean l) {
        String string = "";
        for(int i = 1; i < 4; i++)
            string += (" + " + Math.pow( super.getX(this.index+1) - super.getX(this.index + (l?0:1)), i) + Character.toString('a' + i) + (l?0:1));

        return "S" + (l?0:1)  + "(x" + super.getX(this.index) + ") = a" + (l?0:1)  + string;
    }

    /* Display the Cubic Spline Derivative of 1st Order (Only R.H.S of Equation) */
    public String S_derived(boolean l, int ord, boolean valued) {
        String string = "";
        for(int i = ord + 1; i < 4; i++)
            string += (" + " + (ord == 1 ? i : factorial(i)) + Character.toString('a' + i) + (l?0:1) + "(" + (valued ? super.getX(this.index+1) : "x" + (this.index+1)) + "-" + Double.toString(super.getX(this.index + (l?0:1))) + ")" + (i-ord == 1 ? "": "^" + (i-ord)) );

        return (ord == 1 ? "": "2") + Character.toString('a' + ord) + (l?0:1)  + string;
    }

    /* Display the Cubic Spline Derivative of 1st Order (Only R.H.S of Equation) */
    public String S_derived_of(boolean l, int ord, boolean valued) {
//        if(!l)
//            return "";
//
        String string = "";
        for(int i = ord + 1; i < 4; i++)
            string += (" + " + (new DecimalFormat("###.###")).format((ord == 1 ? i : factorial(i)) * Math.pow( super.getX(this.index+1) - super.getX(this.index + (l?0:1)), i-ord)) + Character.toString('a' + i) + (l?0:1) );

        return (ord == 1 ? "": "2") + Character.toString('a' + ord) + (l?0:1)  + string;
    }

    /* Display one of two conditions involved with derivatives of Cubic Spline Equation for L.H.S. Equality */
    public String printEquality(int order) { return printOrder(true, order, false) + " = " + printOrder(false, order, false); }
    /* Display one of the derivative conditions for the Cubic Spline Equation for R.H.S Equality */
    public String printEqualityEquation(int order) { return S_derived_of(true, order, true) + " = " + S_derived_of(false, order, true); }
    /* Display both L.H.S and R.H.S of the equation */
    public String S(boolean l, int order) { return printOrder(l, order, false) + " = " + S_derived(l, order, true); }
    /* Display L.H.S of one of the equation */
    public String printOrder(boolean l, int order) { return "S'" + (order==1? "" : "'") + (l?0:1) + "(x)"; }
    public String printOrder(boolean l, int order, boolean first) { return "S'" + (order==1? "": "'") + (l?0:1) + "(" + super.getX(this.index+1) + ")"; }
    /* Computing Factorial of N */
    public int factorial(int n) { if(n == 1) return 1; return n * factorial(n-1); }
    /* Print Entire Equality Description of a Specific Order in Spline Interpolation */
    public String splineEquality(int i) { return "\nFor: " + printEquality(i) + "\n" + S(true, i) + S(false, i) + "\n\n" + printEqualityEquation(i); }
    /* Set Value to approximate value of f(value) */
    public void setValue(double value) { System.out.printf("Enter the value of x at which you want to approximate f(x): "); this.value = new Scanner(System.in).nextDouble(); }
    public void setValue(boolean followup) {
        setValue(this.value);
        this.index = -2;
        if(followup) {
            for(Coordinate coordinate: super.dataset) {
                System.out.println(coordinate.x);
                if(coordinate.x > value)
                    break;

                this.index++;
            }
        }

        if(this.index < 0)
            this.index++;
    }

    @Override
    public String toString() { return super.toString(); }
}
