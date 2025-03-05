package com.MuhammedSosun.tutorials.week_5;

@FunctionalInterface
interface MyFunctionalInterface {
    void showMessage(String message);
}


public class lambda {

    public static void main(String[] args) {
        MyFunctionalInterface messagePrinter = (message) -> System.out.println("Mesaj: " + message);
        messagePrinter.showMessage("Merhaba Lambda!");
    }
}