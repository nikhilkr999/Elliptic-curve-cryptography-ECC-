package ellipticCurves;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ECC ecc = new ECC(2, 2);
		Point generator = new Point(5, 1);
		
		Point res = ecc.pointAddition(generator, generator);
		
		System.out.println("x3= " +res.getX()+ ", y3= "+ res.getY());
		
		//System.out.println(ecc.doubleAndAdd(100, generator));
		
		Random random = new Random();
		//ECDH
		//random number for Alice
		int a = random.nextInt(1000);
		
		//random number for Bob
		int b = random.nextInt(1000);
		
		//public keys with double and add algorithm
		Point alicePublicKey = ecc.doubleAndAdd(a, generator);
		Point bobPublicKey = ecc.doubleAndAdd(b, generator);
		
		//they generate the same private key used during encryption
		Point aliceSecretKey = ecc.doubleAndAdd(a, bobPublicKey);
		Point bobSecretKey = ecc.doubleAndAdd(b, alicePublicKey);
		
		System.out.println(aliceSecretKey);
		System.out.println(bobSecretKey);
		
	
	}

}
