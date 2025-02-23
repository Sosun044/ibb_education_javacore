package com.MuhammedSosun._week_3;

import java.util.Scanner;

public class _example_week3_1 {



    private static final int MAX_DECIMAL_ATTEMPTS = 3;
    private static final int MAX_RECURSİVE_LIMIT = 20;
    private static final Scanner scanner = new Scanner(System.in);

    private static int getChoice() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Geçersiz numara girdiniz!!");
                scanner.next();
            }
        }
    }


    public static int IterativeFactoriel(int n){

        for (int i = n-1; i > 0; i--) {
            n *=i;
        }
        return n;
    }
    public static int RecursiveFactoriel(int n){

        if (n == 1 || n == 0){
            return 1;
        }
        return n * RecursiveFactoriel(n-1);
    }
    public static void Choice(){
        while (true) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Please enter a choice: ");
            int choice = scanner1.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(IterativeFactoriel(getChoice()));
                    break;
                case 2:

                    System.out.println(RecursiveFactoriel(getChoice()));
                    break;
                default:
                    System.out.println("Sistemden çıkıldı: ");
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Choice();


    }
}
