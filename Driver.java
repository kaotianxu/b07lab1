import java.nio.channels.Pipe;
import java.util.Arrays;
import java.io.*;

import javax.annotation.processing.SupportedOptions;
import javax.sound.sampled.SourceDataLine;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = {1,2,1};
        int[] e1 = {0,1,2};
        Polynomial p1 = new Polynomial(c1,e1);
        System.out.println(p1.evaluate(1));
        double[] c2 = {3,3,3,3};
        int[] e2 = {0,1,2,3};
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("coefficients and exponents after adding:");
        System.out.println(Arrays.toString(p1.add(p2).coeff));
        System.out.println(Arrays.toString(p1.add(p2).exp));
        System.out.println("coefficients and exponents after multiplying:");
        System.out.println(Arrays.toString(p1.multiply(p2).coeff));
        System.out.println(Arrays.toString(p1.multiply(p2).exp));
        double x = 5;
        double root = -1;
        System.out.printf("p1 has value %f at x = %f\n", p1.evaluate(x), x);
        System.out.printf("does p1 have root at x = %f?\n", root);
        System.out.println(p1.hasRoot(root));
        System.out.println("p1 save to test1.txt");
        p1.saveToFile("test1.txt");
        System.out.println("p3 read from\" 1-2x1+1x2\"");
        Polynomial p3 = new Polynomial(new File("test1.txt"));
        // System.out.println(Arrays.toString(p3.coeff));
        // System.out.println(Arrays.toString(p3.coeff));


        

       
    }
}