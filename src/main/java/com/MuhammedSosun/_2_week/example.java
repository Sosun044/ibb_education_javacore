package com.MuhammedSosun._2_week;

import java.util.Scanner;
import java.lang.Math;



public class example {





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
        //take the inverse of a number
        /*
        int number ,a=0;
        System.out.println("Enter a number: ");
        number = scanner.nextInt();
        while (number>0){

            a = a * 10;
            a = (number % 10) + a;
            number = number / 10;

        }
        System.out.println(a);

         */
        //Armstrong numbers
        /*
        int number;
        double b =0;
        number=scanner.nextInt();
        int first = number;
        while (number > 0){
            int a = 0;
            a = number%10 + a;
            number = number / 10;
            b = Math.pow(a,3) + b;
        }
        if (first != b){
            System.out.println("is not Armstrong  number");
        }
        else {
            System.out.println("Armstrong  number : "+ b );
        }

         */

        //Binary Numbers
        /*
        Array kullanarakta yapılır sadece output olusturmak için yapılmıştır bir çok method bulunur
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(Integer.toBinaryString(number));  // Java'nın hazır metodu
         */
        /*
        int number,k = 0;
        StringBuilder stringBuilder = new StringBuilder();
        number = scanner.nextInt();
        while (number >0){
            if (number % 2 == 1){
                stringBuilder.append("1");
            }
            else {
                stringBuilder.append("0");
            }
            number = number / 2;
        }
        System.out.println(stringBuilder);

         */







    }

