package com.MuhammedSosun._1_part_javaTypes;

import java.util.Scanner;

public class Examples {

    public static void main(String[] args) {
        /*
        //1 bilinmeyenli denklem çözme
        int a,b,x;
        System.out.println("solve this equation ax + b = 0: ");

        Scanner first = new Scanner(System.in);
        System.out.println("Enter number of a: ");
        a = first.nextInt();
        System.out.println(" Enter number of b: ");
        b = first.nextInt();

        //formül
        x = -b / a;

        System.out.println("x is :" + x);
        */
        /*
        //Fahrenheit Dönüştürücü
        int c,f;
        System.out.println("Please enter the value of Celsius");
        Scanner value = new Scanner(System.in);
        c =value.nextInt();
        //Hesaplama
        f = (c * (9/5)) + 32;
        System.out.println("Fahrenheit : " + f);
        */
        //faktöriyel bulma
        int a,temp = 1;
        System.out.println("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
        a =scanner.nextInt();
        //Hesaplama
        for (int i = 1 ; i <= a ; i++) {
            temp = temp * i;

        }
        System.out.println("answer is : " + temp);

        scanner.close();
    }
}
