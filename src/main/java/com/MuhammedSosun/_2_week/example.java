package com.MuhammedSosun._2_week;

import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
        int n,sum= 0;

        System.out.println("please enter a number but do not enter zero(0): ");
        n = scanner.nextInt();


        for (int i = 1; i <= n; i++) {
            if(i == 47) {
                System.out.println("in your number there is 47 so we past 47");
                continue;
            }
            if (i>100) {
                System.out.println("your number is bigger than 100 so just we add to 100");
                break;
            }

            sum = i + sum;
        }

        System.out.println("sum : " + sum);
        if (sum % 2 == 0){
            System.out.println("Number is even number");
        }
        else {
            System.out.println("Number is odd number");
        }

         */
        /* prime number
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        boolean temp = true;
        for (int i = 2; i < n; i++) {

            if (n % i == 0) {
                temp = false;
                break;
            }
        }
        if (temp == true){
            System.out.println("The number is  prime number");
        }
        else {
            System.out.println("The number is not prime number");
        }
        */
        /*
        int n=scanner.nextInt();
        int firstTerm = 0,secondTerm = 1;

        for (int i = 0; i < n; i++) {
            System.out.println(firstTerm + ",");

            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }

         */
        /*
        int number ,digits = 0;
        System.out.println("Enter a number: ");
        number = scanner.nextInt();
        while (number >=10) {
            digits = digits + (number % 10);
            number = number/10;
        }
        digits = digits + number;
        System.out.println(digits + " sum of digits");

         */

        int number ,digits = 0;
        System.out.println("Enter a number: ");
        number = scanner.nextInt();



    }
}
