package com.MuhammedSosun;

import com.MuhammedSosun.controller.StudentController;
import com.MuhammedSosun.controller.TeacherController;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static void chooise() {
        while (true) {
            try {
                System.out.println("\n===== ÖĞRETMEN YÖNETİM SİSTEMİ =====");
                System.out.println("1. Öğretmen Ekle");
                System.out.println("2. Öğrenci Ekle");
                System.out.println("3. Çıkış");
                System.out.print("\nSeçiminizi yapınız: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> teacher();
                    case 2 -> student();
                    case 3 -> {
                        System.out.println("Sistemden Çıkış Yapılıyor");
                        return;
                    }
                    default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } catch (Exception e) {
                System.out.println("⛔ Beklenmeyen bir hata oluştu: " + e.getMessage());
                scanner.nextLine(); // Scanner'ı temizle
            }
        }
    }
    private static void student(){
        try {
            StudentController studentController = new StudentController();
            studentController.chooise();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void teacher(){
        try {
            TeacherController teacherController = new TeacherController();
            teacherController.chooise();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        chooise();
    }
}