package com.MuhammedSosun.tutorials._1_part_javaTypes;

public class system {
    //difference between break and System.exit();
    public static void processClose(){
        System.out.println("Program is starting");
        System.exit(0);
        System.out.println("Program is continue?");
    }
    public static void processforBreak(){
        for (int i = 0; i < 10; i++) {
            if (i == 5){

                System.out.println(i);
                break;
            }


        }
        System.out.println("program bitmedi");
    }

    public static void main(String[] args) {
        //processClose();
        processforBreak();
    }
}
