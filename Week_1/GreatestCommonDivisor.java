import java.util.Scanner;

public class Operater{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter first interger:");
		int figureOne = input.nextInt();
		System.out.print("Enter second interger:");
		int figureTwo = input.nextInt();
		
		int gcd = 1;
		
		for (int k = 2; k <= figureOne/2 && k <= figureTwo/2 ;k++)
			if (figureOne % k == 0 && figureTwo % k == 0)
				gcd = k;
		
		System.out.println("The greatest common divisor is " + gcd);
	}
}