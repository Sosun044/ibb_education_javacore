package com.MuhammedSosun.tutorials.week_5;

import com.MuhammedSosun.Utils.SpecialColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {
    public static List<String> EskiYontem(){
        List<String> m = new ArrayList<>();
        m.add("Ali");
        m.add("Kemal");
        m.add("Hayat");
        m.add("Ayfer");
        m.add("Ayşe");
        m.add("Burak");
        m.add("arif");

        for (String isim :m){
            if (isim.startsWith("A")){
                System.out.println(isim);
            }
        }
        return m;

    }
    public static List<String> YeniYontem(){
        List<String> isimler = Arrays.asList("Kemal","Ali","Ayfer","Fuat","Ayşe","arif");
        List<String> m = isimler.stream().filter(temp -> temp.toUpperCase().startsWith("A")).collect(Collectors.toList());
        return m;

    }

    public static void main(String[] args) {
        System.out.println(SpecialColor.YELLOW +"Eski Yöntem"+SpecialColor.RESET);
        EskiYontem();
        System.out.println(SpecialColor.YELLOW +"Yeni Yöntem"+SpecialColor.RESET);
        System.out.println(YeniYontem());
    }

}
