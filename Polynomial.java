import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {
	
	double[] coeff;
	int[] exp;
	public Polynomial() {
		this.coeff = new double[1];
		this.coeff[0] = 0.0;
		this.exp = new int[1];
		this.exp[0] = 0;
	}
	public Polynomial(double[] arr) {
		coeff = arr;
	}
	public Polynomial(double[] arr, int[] arr1){
		this.coeff = arr;
		this.exp = arr1;
	}
	public Polynomial(File file){
		try {
			Scanner scanner = new Scanner(file);
			String s = scanner.nextLine();
			String[] res = s.split("+|-");
			this.coeff = new double[res.length];
			this.exp = new int[res.length];
			for (int i = 0; i < res.length; i++) {
				if (res[i].contains("x")){
					
					String[] res2 = res[i].split("x");
				
					this.coeff[i] = Double.parseDouble(res2[0]);
					this.exp[i] = Integer.parseInt(res2[1]);
				}
				else {

					this.coeff[i] = Double.parseDouble(res[i]);
					this.exp[i] = 0;
				}
			}
		} catch (Exception e) {
			// System.out.println("exception");
		}
		
		
	}
	public Polynomial add(Polynomial m) {
		Polynomial p = new Polynomial(m.coeff, m.exp);
		int found = 0;
		if (p.coeff.length == 1 && p.coeff[0] == 0.0){
			return this;
		}
		else {
			for (int i = 0; i < coeff.length; i++) {
				found = 0;
				for (int j = 0; j < p.coeff.length; j++) {
					if (exp[i] == p.exp[j]){
						// System.out.printf("this: %d,p: %d, add %f\n",i ,j, coeff[i]);
						p.coeff[j]+=coeff[i];
						found = 1;
						break;
					}
				}
				if (found == 0){
					int[] n = new int[p.exp.length+1];
					double[] nn = new double[p.exp.length+1];
					for (int j = 0; j < p.exp.length; j++) {
						n[j] = p.exp[j];
					}
					n[p.exp.length] = exp[i];
					p.exp = n;
					for (int j = 0; j < p.coeff.length; j++) {
						nn[j] = p.coeff[j];
					}
					nn[p.coeff.length] = coeff[i];
					p.coeff = nn;
				}
			}
			return p;
		}
	}
	public double evaluate(double x) {
		if (coeff.length == 0) {
			return 0.0;
		}
		else {
			double r = 0.0;
			for(int i = 0; i < coeff.length; i++) {
				r += coeff[i] * Math.pow(x, exp[i]);
			}
			return r;
		}
		
	}
	public boolean hasRoot(double x) {
		return (Math.abs(this.evaluate(x)) < 0.000000001);
	}
	public Polynomial multiply(Polynomial p){
		ArrayList<Double> resultCoeff = new ArrayList<>();
		ArrayList<Integer> resultExp = new ArrayList<>();
		for (int i = 0; i < this.coeff.length; i++) {
			for (int j = 0; j < p.coeff.length; j++) {
				int a = this.exp[i] + p.exp[j];
				double b = this.coeff[i] * p.coeff[j];
				if (resultExp.contains(a)){
					int index = resultExp.indexOf(a);
					resultCoeff.set(index, resultCoeff.get(index)+b);
				}
				else {
					resultExp.add(a);
					resultCoeff.add(b);
				}
			}
		}
		double[] rcoeff = new double[resultCoeff.size()];
		int[] rexp = new int[resultCoeff.size()];
		for (int i = 0; i < rcoeff.length; i++) {
			rcoeff[i] = resultCoeff.get(i);
			rexp[i] = resultExp.get(i);
		}
		return new Polynomial(rcoeff, rexp);
		
	}
	public void saveToFile(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(name));
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < coeff.length; i++) {
				if (coeff[i] != 0){
					if (!builder.isEmpty()){
						if (coeff[i] > 0){
							builder.append('+');
						}
						else {
							builder.append('-');
						}
					}
				}
				if (exp[i] == 0) {
					if (coeff[i] % 1 == 0){
						builder.append((int)coeff[i]);
					}
					else {
						builder.append(coeff[i]);
					}
				}
				else {
					if (coeff[i] % 1 == 0){
						builder.append((int)coeff[i]);
					}
					else {
						builder.append(coeff[i]);
					}
					
					builder.append('x');
					if (exp[i] % 1 == 0){
						builder.append((int)exp[i]);
					}
					else {
						builder.append(exp[i]);
					}

				}
			}
			out.write(builder.toString());
			out.close();
			// System.out.println("success");
			return;

		}
		catch(IOException e){
			System.out.println(e.toString());
		}

	}

	// public static void main(String[] args) {
    //     Polynomial p1 = new Polynomial(new double[]{2, 3, 1}, new int[]{2, 1, 0});
    //     Polynomial p2 = new Polynomial(new double[]{-4, 5, -2}, new int[]{2, 1, 0});
	// 	Polynomial p3 = new Polynomial(new double[]{5, 1});
	// 	Polynomial p4 = new Polynomial(new File("\\test1.txt"));

    //     // Polynomial sum = p1.add(p2);
    //     // double result = p1.evaluate(1.8880000000001);
    //     // boolean hasRoot = p1.hasRoot(1.520001);
	// 	// Polynomial r = p2.multiply(p3);

    //     // System.out.println("Sum of p1 and p2: " + Arrays.toString(sum.coeff));
    //     // System.out.println("Evaluation of p1 for x = -1: " + result);
    //     // System.out.println("Does p1 have a root at x = 2? " + hasRoot);
	// 	// System.out.println("r = " + Arrays.toString(r.coeff));
	// 	p1.saveToFile("111hello.txt");
	// 	// System.out.println("sum: " + Arrays.toString(pr.coeff));
	// 	// System.out.println("sum: " + Arrays.toString(pr.exp));
	// 	// System.out.println(Arrays.toString(p4.coeff));
	// 	// System.out.println(Arrays.toString(p4.exp));
    // }
	    
	
}