package com.MuhammedSosun._2_week;
import java.util.Random;

public class Arrays {
    public static String[] arrayMethod1() throws ArrayIndexOutOfBoundsException{
        String[] city = new String[6]; //10 elemanlı
        city[0]="Ankara";
        city[1]="Istanbul";
        city[2]="Agri";
        city[3]="Izmir";
        city[5]="Kocaeli";
        System.out.println(city[3]);

        return city;
    }
    public static void arrayMethod2(){
        String[] city = arrayMethod1();

        for(String temp:  city){
            System.out.println(temp);
        }
    }
    public static int Random(){
        Random random = new Random();
        int rndInt = random.nextInt(9)+1;
        return rndInt;
    }
    public static void arrayMethod3(){
        int[] number = new int[7];
        java.util.Arrays.fill(number,Random());

    }


    public static void main(String[] args) {
       // arrayMethod1();
        arrayMethod2();
        arrayMethod3();
    }
}
