/*
*在控制台中运行的简单0至100猜数字程序
*/
import java.util.Scanner;

public class GuessNumber {
	public static void main(String[] args) {
		int trueNumber = (int)(Math.random()*100);
		Scanner input = new Scanner(System.in);
		
		System.out.println("Guess a number between 0 to 100");
		int guessNumber = input.nextInt();
		
		while(guessNumber != trueNumber) {
			if(guessNumber < trueNumber) {
				System.out.println("Sorry! your guessNumber is too small！");
				System.out.print("Please guess again: ");
			} else {
				System.out.println("Sorry! your guessNumber is too big!");
				System.out.print("Please guess again: ");
			}
			guessNumber = input.nextInt();
		}
		
		System.out.println("Yes! the number is " + trueNumber);
	}
}