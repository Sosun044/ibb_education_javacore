package com.MuhammedSosun.tutorials.week_5;

public class Generics<T> {
    private String name;
    private T surname;

    public Generics() {
    }

    public Generics(String name, T surname) {
        this.name = name;
        this.surname = surname;
    }

    public  void fulldata(T data){
        System.out.println(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getSurname() {
        return surname;
    }

    public void setSurname(T surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Generics{" +
                "name='" + name + '\'' +
                ", surname=" + surname +
                '}';
    }

    public static void main(String[] args) {
        Generics generics = new Generics();
        generics.setName("kemal");
        System.out.println(generics.getName());

        Generics generics2 = new Generics();
        generics2.setName("Muhammed");
        generics2.setSurname("Sosun");
        System.out.println(generics2);
        System.out.println(generics2.getName() + " " + generics2.getSurname());

        generics2.fulldata(888888 + "   kemal");
    }
}
