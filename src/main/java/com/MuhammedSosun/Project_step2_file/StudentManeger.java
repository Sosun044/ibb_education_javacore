package com.MuhammedSosun.Project_step2_file;

import com.MuhammedSosun.Project_step2_file.StudentNotFoundException;
import com.MuhammedSosun.Utils.SpecialColor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManeger {
    private ArrayList<StudentDto> studentDtoList = new ArrayList<>();
    private int studentCounter = 0;
    private static final String FILE_NAME = "student.txt";

    static {
    }

    public StudentManeger() {
        loadStudentListFromFile();
    }

    private void saveToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            objectOutputStream.writeObject(studentDtoList);
        } catch (IOException io) {
            System.out.println(SpecialColor.RED + " Dosya Ekleme Hatası" + SpecialColor.RESET);
            io.printStackTrace();
        }
    }

    private void loadStudentListFromFile() {
    }

    public void add(StudentDto dto) {
        studentDtoList.add(new StudentDto(++studentCounter, dto.getName(), dto.getSurname(), dto.getMidTerm(), dto.getFinalTerm(), dto.getBirthDate()));
        System.out.println(SpecialColor.YELLOW + "Öğrenci Eklendi" + SpecialColor.RESET);

        saveToFile();
    }

    public void list() {
        if (studentDtoList.isEmpty()) {
            System.out.println(SpecialColor.RED + " Öğrenci Yoktur" + SpecialColor.RESET);
        } else {
            System.out.println(SpecialColor.BLUE + "Öğrenci Listesi" + SpecialColor.RESET);
            studentDtoList.forEach(System.out::println);
        }
    }

    public void search(Integer id) {
        /*studentDtoList.stream().filter(temp ->temp.getName().equalsIgnoreCase(name))
                .forEach(System.out::println); */
        boolean found = studentDtoList.stream().filter(temp -> temp.getId().equals(id))
                .peek(System.out::println)
                .findAny()//Data var mı yok mu
                .isPresent();
        if (!found) {
            throw new StudentNotFoundException(id + " id li öğrenci Bulunamadı");
        }
    }

    public void update(int id, StudentDto dto) {
        for (StudentDto temp : studentDtoList) {
            if (temp.getId() == id) {
                temp.setName(dto.getName());
                temp.setSurname(dto.getSurname());
                temp.setBirthDate(dto.getBirthDate());
                temp.setMidTerm(dto.getMidTerm());
                temp.setFinalTerm(dto.getFinalTerm());
                //Güncellenmiş öğrenci bilgileri
                System.out.println(SpecialColor.BLUE + temp + "Öğrenci Bilgileri Güncellendi" + SpecialColor.RESET);
                //Dosayay kaydet
                saveToFile();
                return;
            }
        }
        System.out.println(SpecialColor.RED + " Öğrenci Bulunamadı " + SpecialColor.RESET);
    }

    public void delete(int id) {
        boolean removed = studentDtoList.removeIf(temp -> temp.getId() == id);
        if (removed) {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silindi " + SpecialColor.RESET);
            // File Ekle
            saveToFile();
        } else {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silinmedi " + SpecialColor.RESET);
        }
    }

    public void chooise() {
        new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        StudentManeger studentManeger = new StudentManeger();

        while (true) {
            System.out.println("1.Öğrenci Ekle");
            System.out.println("2.Öğrenci Listele");
            System.out.println("3.Öğrenci Ara");
            System.out.println("4.Öğrenci Güncelle");
            System.out.println("5.Öğrenci Sil");
            System.out.println("6.Öğrenci Toplam Öğrenci Sayısı");
            System.out.println("7.Öğrenci Rastgele");
            System.out.println("8.Öğrenci Not Hesapla");
            System.out.println("9.Öğrenci En yüksek ve en düşük notları göster");
            System.out.println("10.Öğrenci sıralaması Doğum gününe göre göster");
            System.out.println("11.Çıkış ");
            System.out.println("Lütfen seçiminizi yapınız:  ");
            int choose = input.nextInt();
            input.nextLine(); // Buffer'ı temizle

            switch (choose) {
                case 1:
                    System.out.println("Öğrenci Adı");
                    String name = input.nextLine();
                    System.out.println("Öğrenci Soyadı");
                    String surname = input.nextLine();
                    System.out.println("Öğrenci Doğum Tarihi");
                    LocalDate birthDate = LocalDate.parse(input.nextLine());
                    System.out.println("Vize Puanı");
                    double midTerm = input.nextDouble();
                    System.out.println("Final Puanı");
                    double finalTerm = input.nextDouble();
                    studentManeger.add(new StudentDto(++studentCounter, name, surname, midTerm, finalTerm, birthDate));
                    break;
                case 2:
                    studentManeger.list();
                    break;
                case 3:
                    studentManeger.list();
                    System.out.println(SpecialColor.BLUE + "Arancak öğrenci id si gir " + SpecialColor.RESET);
                    Integer searchid = input.nextInt();
                    studentManeger.search(searchid);
                    break;
                case 4:
                    System.out.println("Güncellenecek Öğrenci id si giriniz: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.println("Yeni Öğrenci Adı");
                    String nameUpdate = input.nextLine();
                    System.out.println("Yeni Öğrenci Soyadı");
                    String surnameUpdate = input.nextLine();
                    System.out.println("Öğrenci Doğum Tarihi YYYY-MM-DD");
                    LocalDate birthDateUpdate = LocalDate.parse(input.nextLine());
                    System.out.println("Vize Puanı");
                    double midTermUpdate = input.nextDouble();
                    System.out.println("Final Puanı");
                    double finalTermUpdate = input.nextDouble();

                    StudentDto studentDtoUpdate = StudentDto.builder().
                            name(nameUpdate)
                            .surname(surnameUpdate)
                            .midTerm(midTermUpdate)
                            .finalTerm(finalTermUpdate)
                            .birthDate(birthDateUpdate).
                            build();
                    try {
                        studentManeger.update(id, studentDtoUpdate);
                    } catch (StudentNotFoundException e) {
                        System.out.println(SpecialColor.RED + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    studentManeger.list();
                    System.out.println("Silinecek Öğrenci Id si giriniz: ");
                    int deleteId = input.nextInt();
                    studentManeger.delete(deleteId);
                    break;
                case 6:
                    System.out.println("Case 6");
                    break;
                case 7:
                    System.out.println("Case 7");
                    break;
                case 8:
                    System.out.println("Case 8");
                    break;
                case 9:
                    System.out.println("Case 9");
                    break;
                case 10:
                    System.out.println("Sistemden çıkış yapınız: ");
                    System.exit(0);
                    //return: bunu yazarsak break gerek yoktur
                    break;
                default:
                    System.out.println("Geçersiz seçim yaptınız lütfen tekrar deneyiniz");
                    break;
            }

        }
    }
}