package com.MuhammedSosun.tutorials._2_week;

public class Strings {
    //I repeat String structures here

    public static void main(String[] args) {
        String vocabulary1 = " I am learNIng Java ";
        String vocabulary2 = " I am learNIng Java ";

        System.out.println("number of letters: " + vocabulary1.length());

        System.out.println("upper case: "+ vocabulary1.toUpperCase());
        System.out.println("lower case: " + vocabulary1.toLowerCase());

        System.out.println("trim removes leading and trailing spaces: "+vocabulary1.trim().length());
        System.out.println("trim removes leading and trailing spaces: " + vocabulary1.trim());
        // you should know this difference between equals and "=="
        System.out.println("== " + vocabulary1 == vocabulary2);
        System.out.println("equals: " + vocabulary1.equals(vocabulary2));
        System.out.println("charAt start counting from indeks 0  "  + vocabulary1.charAt(3));
        System.out.println(vocabulary1.substring(1));

        //contains
        System.out.println("this is : " + vocabulary1.contains("Java"));

        //replace
        System.out.println("Replace a vocabulary: " + vocabulary1.replace("Java","Java 2025"));
        //replace is just one time

        String concatenation = vocabulary1 + vocabulary2;
        System.out.println(concatenation);
        System.out.println(vocabulary1.concat(vocabulary2));


    }



}
