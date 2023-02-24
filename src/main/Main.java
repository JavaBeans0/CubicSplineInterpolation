package main;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interpolation.Spline;

public class Main {

    public static Spline spline;

    public static void callThis() {
        while(true) {
            System.out.print("Enter value: ");
            double x = (new Scanner(System.in)).nextDouble();

            if(x < 0)
                break;

            System.out.println("Answer: " + (2.42211 - x));
        }
    }
    public static void main(String[] args) {
        
        System.out.println("Welcome to the Cubic Spline Interpolation. ");
        System.out.println("(1) Manual Data Entry (2) Automation ");
        System.out.print("Enter your choice: ");
        int choice = (new Scanner(System.in)).nextInt();

        if (choice == 1) {
            System.out.println("Enter the number of coordinates for your dataset: ");
            spline = new Spline((new Scanner(System.in)).nextInt());
        } else {
            try {
                spline = new Spline();
                BufferedReader bin = new BufferedReader(new FileReader("/Users/naimul7/JavaProjects/CubicSplineInterpolation/src/main/data.txt"));

                String strX = "";
                String strY = "";
                for(int counter = 0; (strY = bin.readLine()) != null; counter++) { /* Assumption of even number of lines in data.txt file with coordinate pairs for test input data */
                    if(counter % 2 == 1)
                        spline.set(Double.parseDouble(strX), Double.parseDouble(strY));
                    else
                        strX = strY;
                }
                bin.close();
            } catch(FileNotFoundException fnfex) { fnfex.printStackTrace();
            } catch(IOException ioex) { ioex.printStackTrace(); }
        }

        System.out.println(spline);

        System.out.println();

        spline.setValue(true);

        System.out.println();

        for(int i = 0; i < 2; i++)  /* Spline Level */
            System.out.println(spline.S_of(i == 0));

        System.out.println(splineEquality());

        System.out.println("\n\nThank you for running this program.\nGoodbye!");
    }

    /* Display all equalities for Spline Cubic Interpolation */
    public static String splineEquality() { return equality(1) + "\n" + equality(2); }
    /* Display entire Equality of a specific order for a Spline Cubic Interpolation */
    public static String equality(int i) {
        return "\nFor: " + spline.printEquality(i) + "\n" + spline.S(true, i) + "\n" + spline.S(false, i) + "\n\n" + spline.printEqualityEquation(i);
    }

    /* Display entire Equality of a specific order for Spline Interpolation using Spline instance */
    public static String equality(Spline spline, int i) {
        return "\nFor: " + spline.printEquality(i) + "\n" + spline.S(false, i) + "\n" + spline.S(true, i) + "\n\n" + spline.printEqualityEquation(i);
    }

    /* Display entire Equality of a specific order for a specific value for Spline Interpolation using Spline instance */
    public static String equalityOf(Spline spline, int i) {
        return "\nFor: " + spline.printEquality(i) + "\n" + spline.S(false, i) + "\n" + spline.S(true, i) + "\n\n" + spline.printEqualityEquation(i);
    }

    public static String splineEquality(Spline spline) {
        return equality(spline, 1) + equality(spline, 2);
    }

    /* Similar Functionality to previous Method */
    public static void spline_equality() {
        for(int i = 1; i < 3; i++) {
            System.out.println("\nFor: " + spline.printEquality(i));
            System.out.println(spline.S(true, i));
            System.out.println(spline.S(false, i));

            System.out.println();

            System.out.println(spline.printEqualityEquation(i));
        }
    }
}
