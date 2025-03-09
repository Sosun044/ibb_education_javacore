package com.MuhammedSosun.tutorials.week_5;
import java.util.Optional;

public class optional {

    public static Optional<String> findByUserId(int id){
        if (id == 1){
            return Optional.of("Muhammed");
        }
        else {
            return Optional.empty();
        }
    }
    public static int getLength(String str){
        return Optional.ofNullable(str).map(String::length).orElse(0);
    }
    ////////////////////////////////////////////////////////////////////////
    public String isNotValidation(String data){
        return data;
    }
    public String validation(String data){
        if (data.isBlank()){
            return "Unknown";
        }
        else {
            return data;
        }
    }
    public Optional<String> optionalResult(String data){
        Optional<String> name = Optional.ofNullable(data);
        return name;
    }
    public static void main(String[] args) {
        optional optional = new optional();
        Optional<String> user = findByUserId(1);
        System.out.println(user.orElse("Kullanıcı Bulunamadı"));

        Optional<String> user2 = findByUserId(2);
        System.out.println(user2.orElse("Kullanıcı Bulunamadı"));



        System.out.println(getLength("Muhammed"));

        System.out.println(optional.isNotValidation("Okunuyor"));
        System.out.println(optional.validation(" "));
        System.out.println(optional.optionalResult(" optional"));

    }
}
