package ChibuzoBuzoBuzo;

import java.util.Scanner;

public class ThreeSmallestLargest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter first number:");
        int firstNumber = input.nextInt();
        System.out.println("Enter second number:");
        int secondNumber = input.nextInt();
        System.out.println("Enter third number:");
        int thirdNumber = input.nextInt();

        int sum = firstNumber + secondNumber + thirdNumber;
        int average = sum / 3;

        if (firstNumber > secondNumber){
         if (firstNumber > thirdNumber)
           System.out.printf("%s%d%n", "the greatest number is : ", firstNumber);
        }
        if (secondNumber > firstNumber){
            if (secondNumber > thirdNumber)
                System.out.printf("%s%d%n", "the greatest number is : ", secondNumber);
        }
        if (thirdNumber > firstNumber){
            if (thirdNumber > secondNumber)
                System.out.printf("%s%d%n", "the greatest number is : ", thirdNumber);
        }
        if (thirdNumber == firstNumber) {
            if (thirdNumber  == secondNumber)
                System.out.println("They are all equal");

        }
        System.out.printf("The sum of the three numbers is: %d%n%s%d%n", sum, "The average is; ", average);
    }
}
