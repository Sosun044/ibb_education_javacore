package com.MuhammedSosun.tutorials.week_6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Student{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void run(){
        System.out.println(name + "Çalışıyor");
    }
}// end class

public class Reflection {
    public static void main(String[] args) {
        // 1. Yöntem: Class.forName()
        //Class<?> studentClass = Class.forName("Student");
        //Class<?> studentClass = Class.forName("com.MuhammedSosun.tutorials._6_week.Student");
        //System.out.println("Sınıf Adı: " + studentClass.getName());

        // 2. Yöntem: .class kullanımı
        Class<?> studentClass2 = Student.class;
        //System.out.println("Sınıf Adı: " + studentClass2.getName());

        // 3. Yöntem: getClass()
        Student student = new Student();
        Class<?> studentClass3 = student.getClass();
        // Sınıf adını yazdır
        System.out.println("Sınıf Adı: " + studentClass3.getName());

        ///////////////////////////////////////////////////////////////
         /*
         import java.lang.reflect.Constructor;
         import java.lang.reflect.Field;
         import java.lang.reflect.Method;
         */

        // Değişkenleri listele
        Field[] fields = studentClass3.getDeclaredFields();
        System.out.println("\n### Değişken Listesi:");
        for (Field field : fields) {
            System.out.println(field);
        }

        // Constructor bilgilerini al
        Constructor<?>[] constructors = studentClass3.getConstructors();
        System.out.println("\n### Constructor Listesi:");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        // Metotları listele
        Method[] methods = studentClass3.getDeclaredMethods();
        System.out.println("\n### Metot Listesi:");
        for (Method method : methods) {
            System.out.println(method);
        }

    }
}
