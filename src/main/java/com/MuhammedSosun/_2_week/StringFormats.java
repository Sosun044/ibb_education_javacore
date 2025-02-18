package com.MuhammedSosun._2_week;

import java.util.Formatter;

public class StringFormats {
    public static final String RESET = "\033[0m";  // Renk sıfırlama
    public static final String RED = "\033[31m";   // Kırmızı
    public static final String GREEN = "\033[32m"; // Yeşil
    public static final String YELLOW = "\033[33m";// Sarı
    public static final String BLUE = "\033[34m";  // Mavi
    public static final String PURPLE = "\033[35m";// Mor
    public static final String CYAN = "\033[36m";  // Açık Mavi
    // formatter1
    public static void formatter1() {
        // Eğer new Formatter yazarsak close() ile manuel kapatmak zorundayız.
        Formatter formatter = new Formatter();
        formatter.format("Merhabalar Adınız: %s, T.C: %d, Fiyat: %f ", "Muhammed", 11223344, 44.23);
        System.out.println(formatter);
        formatter.close(); // Belleği serbest bırakma
        System.out.println();
    }

    // formatter2 (Best Practice)
    public static void formatter2() {
        // Eğer String.format() yazarsak kapatmak zorunda değiliz GC(Garbarage Collector) otomatik çalışır
        String formatterString = String.format("Merhabalar Adınız: %s, T.C: %d, Fiyat: %f ", "Muhammed", 11223344, 44.23);
        System.out.println(formatterString);
        System.out.println();
    }

    // formatter (Best Practice) [String.format()]
    public static void formatter3() {
        String formatterString = String.format("Merhabalar Adınız: %s, T.C: %d, Fiyat: %f ", "Muhammed", 11223344, 44.23);
        System.out.println(formatterString);
        System.out.println();
    }

    // formatter (Best Practice) Color
    public static void formatter4() {
        String formatterString = String.format("Merhabalar Adınız: %s, T.C: %d, Fiyat: %f ", "Muhammed", 11223344, 44.23);
        System.out.println(formatterString);
        System.out.println(YELLOW+formatterString+RESET);

        String formatterString2 = String.format("Merhabalar:"+ GREEN+"Adınız: %-10s"+RESET+":"+RED +"T.C: %10d"+RESET+":"+YELLOW+ "Fiyat: %.4f "+RESET+":" , "Muhammed", 11223344, 44.23);
        System.out.println(formatterString2);
        System.out.println();
    }

    // formatter4 (printf)
    public static void formatter5() {
        String name = "Muhammed";
        int tcNumber = 11223344;
        double price = 44.23;
        System.out.printf("Merhabalar Adınız: %s, T.C: %d, Fiyat: %f ", name, tcNumber, price);
        System.out.println();
        System.out.printf("Merhabalar Adınız: %10s, T.C: %d, Fiyat: %f ", name, tcNumber, price); //%10s: Sağdan 10 karakter genişliğinde
        System.out.println();
        System.out.printf("%-15s %-15d %.3f ", name, tcNumber, price);//%-15s: Sola doğru yasla
        System.out.println();
    }

    public static void main(String[] args) {
        // formatter1();
        //formatter2();
        //formatter3();
        formatter4();
        //formatter5();
    }
}
