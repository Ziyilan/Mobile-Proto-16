public class Fraction {

    private int numerator;
    private int denominator;

	public Fraction(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public void simplify(){
		int gcd = this.gcd(numerator, denominator);
		numerator = numerator/gcd;
		denominator = denominator/gcd;
	}
	
	public Fraction add(Fraction f2){
		int df2 = f2.getDenominator();
		int nf2 = f2.getNumerator();
		int newD = df2 * denominator;
		int newN = df2 * numerator + denominator * nf2;
		Fraction res = new Fraction(newN, newD);
		res.simplify();
		return res; 
	}
	
	public int getNumerator(){
		return numerator; 
	}
	
	public int getDenominator(){
		return denominator;
	}
	
	public static int gcd(int m, int n){
		if (n==0) return m;
		return gcd(n, m%n);
	}
	
    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }
    
}
