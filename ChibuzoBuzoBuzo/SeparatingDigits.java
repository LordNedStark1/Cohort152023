package ChibuzoBuzoBuzo;

import java.util.Scanner;

public class SeparatingDigits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first number:");
        int firstNumber = input.nextInt();
        System.out.println("Enter second number:");
        int secondNumber = input.nextInt();
        System.out.println("Enter third number:");
        int thirdNumber = input.nextInt();
        System.out.println("Enter forth number:");
        int forthNumber = input.nextInt();
        System.out.println("Enter fifth number:");
        int fifthNumber = input.nextInt();

        System.out.printf("%d %d %d %d %d %n", firstNumber, secondNumber, thirdNumber, forthNumber, fifthNumber);
    }
}
