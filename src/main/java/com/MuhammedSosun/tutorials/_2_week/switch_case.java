package com.MuhammedSosun.tutorials._2_week;

import java.util.Scanner;

public class switch_case {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please Enter a number: ");
        int number = scanner.nextInt();


        switch (number){
            case 1:
                System.out.println("Number is : 1");
                break;
            case 2:
                System.out.println("Number is : 2");
                break;
            case 3:
                System.out.println("Number is : 3");
                break;
            case 4:
                System.out.println("Number is : 4");
                break;
            case 5:
                System.out.println("Number is : 5");
                break;
            default:
                System.out.println(number + "not between 1-5");
                break;
        }
    }
}
