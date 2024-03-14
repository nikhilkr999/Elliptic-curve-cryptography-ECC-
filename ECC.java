package ellipticCurves;

//equation of elliptic curve y^2 = x^3 + ax + b
public class ECC {
	private double a;
	private double b;
	
	public ECC(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public Point doubleAndAdd(int n, Point p) {
		Point temp = new Point(p.getX(),p.getY());
		String nBinary = Integer.toBinaryString(n);
		
		for(int i=1; i<nBinary.length(); ++i) {
			int bit = Integer.parseInt(""+nBinary.charAt(i));
			
			//point doubling operation
			temp = pointAddition(temp, temp);
			
			if(bit == 1) {
				//point addition
				temp = pointAddition(p, temp);
			}
		}
		
		return temp;
	}
	
	public Point pointAddition(Point p, Point q) {
		double x1 = p.getX();
		double y1 = p.getY();
		double x2 = q.getX();
		double y2 = q.getY();
		
		double m = 0;    //slop m
		if(Double.compare(x1, x2)==0 && Double.compare(y1, y1)==0 ) {
			//point doubling : p=q
			m = (3*x1*x1+a)/(2*y1);
		}
		else {
			//point addition : p!=q
			m = (y2 - y1)/(x2 - x1);
		}
		
		//calculating point R
		double x3 = m*m - x1 - x2;
		double y3 = m*(x1 - x3) - y1;
		
		return new Point(x3, y3);
	}
}
