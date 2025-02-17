package com.MuhammedSosun._2_week;

import java.io.IOException;
import java.util.Scanner;

public class Exceptions {
    public static void main(String[] args) throws ArithmeticException, IOException,NullPointerException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number1: ");
        int number1 = scanner.nextInt();
        System.out.println("Enter number2: ");
        int number2 = scanner.nextInt();
        try {
          int result =   number1/number2;
            System.out.println(result);
            scanner.close();

        }catch (ArithmeticException ai){
            ai.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("ther is exceptional or no exceptional everytime must be job");

        }
        System.out.println("there is a lot of trees");
    }

}
