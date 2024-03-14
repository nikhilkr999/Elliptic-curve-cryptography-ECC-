package ellipticCurves;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Generator {
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		System.out.println("let the Elliptic Curve be : y^2 = x^3 + ax + b mod r ");

		int a=2, b=2, r=17;

		System.out.println("x1 value of Point P?");
		int x1P = key.nextInt();
		System.out.println("y1 value of Point P?");
		int y1P = key.nextInt();
		
		
		System.out.println("x2 value of Point Q?");
		int x2Q = key.nextInt();
		System.out.println("y2 value of Point Q?");
		int y2Q = key.nextInt();
		System.out.println("Point P = [" + x1P + "," + y1P + "]");
		System.out.println("Point Q = [" + x2Q + "," + y2Q + "]");
		Map<String, int[]> valueP = new HashMap<>();
		
		int[] p = new int[2];
		
		if(Double.compare(x1P, x2Q)==0 && Double.compare(y1P, y2Q)==0 ) {
			int s1 = Mod(((3 * (x1P * x1P)) + a), r);
			int s2 = modInverse((2 * y1P), r);
			int s = (s1 * s2) % r;
			
			int x2q = x1P;
			int[] point = nextP(r, x1P, y1P, x2q, s);
			System.out.println("Point R = [" + point[0] + "," + point[1] + "]");
			p=point;
			
		}
		else {
			int[] point = NextPValueAddition(a, b, r, x1P, y1P, x2Q, y2Q);
			System.out.println("Point R = [" + point[0] + "," + point[1] + "]");
			p=point;
		}
		ECC_POINTS_GENERATE.getAllP(a, b, r, p[0], p[1], valueP);
		System.out.println("Enter an integer ");
		int i = key.nextInt();
		for(Map.Entry<String, int[]> e: valueP.entrySet()) {
			if(e.getKey().equals(i+"P")) {
				System.out.println("["+e.getValue()[0]+", "+e.getValue()[1]+"]");
			}
		}
	}
	static int Mod(int A, int B) {
		int ans = A % B;
		if (ans < 0) {
			ans = B + ans;
		}
		return ans;
	}

	static int modInverse(int A, int M) {               //A=4= 1/4 = 4^-1  = (4 * x)mod M == 1   x is the inverse. 
		for (int X = 1; X < M; X++)
			if (((A % M) * (X % M)) % M == 1)
				return X;
		return 1;
	}
	private static int[] nextP(int r, int x1P, int y1P, int x2Q, int s) {
		int x3 = Mod(((s * s) - x1P - x2Q), r);
		int y3 = Mod(((s * (x1P - x3)) - y1P), r);

		int[] nextP = { x3, y3 };
		return nextP;
	}
	private static int[] NextPValueAddition(int a, int b, int r, int x1P, int y1P, int x2Q, int y2Q) {
//        int s1= Math.ceilMod((y2Q-y1P) , r );
	int s1 = (y2Q - y1P);

	int s2 = (x2Q - x1P);

	if (s1 < 0 && s2 < 0) {
		s1 = s1 * (-1);
		s2 = s2 * (-1);
	} else if (s2 < 0 && s1 >= 0) {
		s1 = s1 * (-1);
		s2 = s2 * (-1);
	}
	s1 = Mod(s1, r);
//        System.out.print("s1 = "+ s1 + " ");

	s2 = modInverse(s2, r);
//        System.out.println("s2 = "+ s2 + " ");

	int s = (s1 * s2) % r;
//        System.out.print("s = "+ s + " ");

	return nextP(r, x1P, y1P, x2Q, s);
}
}
