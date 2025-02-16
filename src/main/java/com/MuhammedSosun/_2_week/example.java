package com.MuhammedSosun._2_week;

import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        int n,sum= 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter a number but do not enter zero(0): ");
        n = scanner.nextInt();


        for (int i = 1; i <= n; i++) {
            if(i == 47)
                continue;
            sum = i + sum;
            if (i>100)
                break;
        }

        System.out.println("toplam : " + sum);
        if (sum % 2 == 0){
            System.out.println("Number is even number");
        }
        else {
            System.out.println("Number is odd number");
        }

    }
}
