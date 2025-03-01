package com.MuhammedSosun.tutorials._1_part_javaTypes;

import java.util.Scanner;

public class _8_Scanner {
    public static void main(String[] args) {

        String name,surname;
        Scanner klavye = new Scanner(System.in);
        int language;

        System.out.println("Lütfen adınızı giriniz: ");
        name = klavye.nextLine();

        System.out.println("Lütfen Soyadınızı giriniz: ");
        surname = klavye.nextLine();

        System.out.println("Bildiğiniz Diller ve Teknolojiler");
        language = klavye.nextInt();

        System.out.println("Adınız: " + name + "Soyadınız: " + surname);

        klavye.close();

    }
}
