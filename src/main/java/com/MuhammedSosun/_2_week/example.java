package com.MuhammedSosun._2_week;

import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        int n,sum= 0;
        Scanner scanner = new Scanner(System.in);
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

    }
}
