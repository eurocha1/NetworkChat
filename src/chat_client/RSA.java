package chat_client;

import java.awt.EventQueue;

import java.io.*;

import java.math.BigInteger;

import java.util.ArrayList;

import java.util.Random;

import java.util.Scanner;





/**

 * Quick and dirty implementation of the RSA algorithm

 * Read through main() for a breakdown on the RSA workings

 */

public class RSA {

	/**

	 * Takes a string and converts each character to an ASCII decimal value

	 * Returns BigInteger

	 */

	public static BigInteger stringCipher(String message) {
		int i = 0;
		BigInteger ch;
		BigInteger total = BigInteger.valueOf(0);
		while (i < message.length()) 
		{
			 ch = (BigInteger.valueOf(message.charAt(i)));
			 total = total.add(ch.multiply(BigInteger.valueOf((long) Math.pow(128, i))));
			 i++;
		}
		return total;
	}


	public static String cipherToString(BigInteger message) {
		String cipher = message.toString(2);
		String output = "";
		String character = "";
		int n = cipher.length() / 7;
		int rem = cipher.length() % 7;
		int x = 0;
		int z = 0;
		int i = 0;
		while (i < cipher.length()) {
			i++;
			
			if(x < n)
			{
				character = cipher.charAt(cipher.length()-i) + character;
				if(i%7 == 0)
				{
					x++;
					output += (char) Integer.parseInt(character, 2);
					character = "";
				}
			}
			else
			{
				character = cipher.charAt(cipher.length()-i) + character;
				z++;
				if(z==rem) 
				{
					output += (char) Integer.parseInt(character, 2);
					character = "";
				}

				
			}
		}
		return output;

	}


	public static BigInteger getPhi(BigInteger p, BigInteger q) {

		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

		return phi;

	}



	/**

	 * Generates a random large prime number of specified bitlength

	 *

	 */

	public static BigInteger largePrime(int bits) {

		Random randomInteger = new Random();

		BigInteger largePrime = BigInteger.probablePrime(bits, randomInteger);

		return largePrime;

	}





	/**

	 * Recursive implementation of Euclidian algorithm to find greatest common denominator

	 * Note: Uses BigInteger operations

	 */

	public static BigInteger gcd(BigInteger a, BigInteger b) {

		if (b.equals(BigInteger.ZERO)) {

			return a;

		} else {

			return gcd(b, a.mod(b));

		}

	}





	/** Recursive EXTENDED Euclidean algorithm, solves Bezout's identity (ax + by = gcd(a,b))

	 * and finds the multiplicative inverse which is the solution to ax ≡ 1 (mod m)

	 * returns [d, p, q] where d = gcd(a,b) and ap + bq = d

	 * Note: Uses BigInteger operations

	 */

	public static BigInteger[] extEuclid(BigInteger a, BigInteger b) {

		if (b.equals(BigInteger.ZERO)) return new BigInteger[] {

			a, BigInteger.ONE, BigInteger.ZERO

		}; // { a, 1, 0 }

		BigInteger[] vals = extEuclid(b, a.mod(b));

		BigInteger d = vals[0];

		BigInteger p = vals[2];

		BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));

		return new BigInteger[] {

			d, p, q

		};

	}





	/**

	 * generate e by finding a Phi such that they are coprimes (gcd = 1)

	 *

	 */

	public static BigInteger genE(BigInteger phi) {

		Random rand = new Random();

		BigInteger e = new BigInteger(75, rand);

		do {

			e = new BigInteger(75, rand);

			while (e.min(phi).equals(phi)) { // while phi is smaller than e, look for a new e

				e = new BigInteger(75, rand);

			}

		} while (!gcd(e, phi).equals(BigInteger.ONE)); // if gcd(e,phi) isnt 1 then stay in loop

		return e;

	}



	public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {

		return message.modPow(e, n);

	}



	public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {

		return message.modPow(d, n);

	}



	public static BigInteger n(BigInteger p, BigInteger q) {

		return p.multiply(q);



	}

}