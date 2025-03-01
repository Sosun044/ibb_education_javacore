package com.MuhammedSosun.tutorials._week_3;

import java.util.Scanner;

public class Pojo {
    //getter setter
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String sifreliSurname(){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i = 0; i < surname.length(); i++) {
            if (i < 3){
                stringBuilder.append(Character.toUpperCase(surname.charAt(i)));
            }
            else {
                stringBuilder.append("*");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pojo kisi = new Pojo();

        System.out.print("Adınızı girin: ");
        kisi.setName(scanner.nextLine());

        System.out.print("Soyadınızı girin: ");
        kisi.setSurname(scanner.nextLine());
        Pojo pojo = new Pojo();
        System.out.println(kisi.sifreliSurname());





    }
}
