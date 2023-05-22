


public class Polynomial {
	
	double[] coeff;
	public Polynomial() {
		coeff = new double[1];
		coeff[0] = 0.0;
	}
	public Polynomial(double[] arr) {
		coeff = arr;
	}
	public Polynomial add(Polynomial p) {
		int len = Math.max(coeff.length, p.coeff.length);
		double[] result = new double[len];
		for (int i = 0; i < coeff.length; i++) {
			result[i] += coeff[i];
		}
		for (int i = 0; i < p.coeff.length; i++) {
			result[i] += p.coeff[i];
		}
		return new Polynomial(result);
	}
	public double evaluate(double x) {
		if (coeff.length == 0) {
			return 0.0;
		}
		else if (coeff.length == 1) {
			return coeff[0];
		}
		else {
			double r = coeff[0];
			for(int i = 1; i < coeff.length; i++) {
				r += coeff[i] * Math.pow(x, i);
			}
			return r;
		}
		
	}
	public boolean hasRoot(double x) {
		return (Math.abs(evaluate(x)) < 0.0000000000001);
	}
	// public static void main(String[] args) {
    //     Polynomial p1 = new Polynomial(new double[]{-5.41810688, 9.304064, -5.296, 1});
    //     Polynomial p2 = new Polynomial(new double[]{-1, 2, 3});

    //     Polynomial sum = p1.add(p2);
    //     double result = p1.evaluate(1.888);
    //     boolean hasRoot = p1.hasRoot(1.52);

    //     // System.out.println("Sum of p1 and p2: " + Arrays.toString(sum.coeff));
    //     System.out.println("Evaluation of p1 for x = -1: " + result);
    //     System.out.println("Does p1 have a root at x = 2? " + hasRoot);
    // }
	    
	
}