package com.MuhammedSosun.Project_step1_file;

import com.MuhammedSosun.Utils.SpecialColor;

import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystem {
    private ArrayList<StudentDto> studentDtoList = new ArrayList<>();
    private int studentCounter = 0;
    private static final String FILE_NAME = "students.txt";

    static {

    }

    public StudentManagementSystem() {
        //prgram başlarken öğrenci listesini hemen yüklesin
        loadStudentListFromFile();
    }
    ////////////////////////////////////////////////////////////////
    // Login
    // Register

    /// /////////////////////////////////////////////////////////////
    // FileIO Create
    // File Create
    private void saveToFile() {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            objectOutputStream.writeObject(studentDtoList);

        }catch (IOException io){
            System.out.println(SpecialColor.RED + " Dosya Ekleme Hatası"+ SpecialColor.RESET);
            io.printStackTrace();

        }
    }

    // File Read
    // Öğrenci Listesini Yükle (Dosya)
    private void loadStudentListFromFile() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            studentDtoList =(ArrayList<StudentDto>) objectInputStream.readObject();
            studentCounter = studentDtoList.size();

        }catch (IOException io){
            System.out.println(SpecialColor.RED + " Dosya Ekleme Hatası"+ SpecialColor.RESET);
            io.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /// /////////////////////////////////////////////////////////////
    // Öğrenci Ekle
    public void add(StudentDto dto){
        studentDtoList.add(new StudentDto(++studentCounter,dto.getName(),dto.getSurname(),dto.getBirthDate(),dto.getGrade()));
        System.out.println(SpecialColor.YELLOW + " Öğrenci Eklendi "+ SpecialColor.RESET);
        //File Ekle
        saveToFile();
    }


    // Öğrenci Listesi
    public void list(){
        if (studentDtoList.isEmpty()){
            System.out.println(SpecialColor.RED + " Öğrenci Yoktur"+SpecialColor.RESET);
        }
        else {
            studentDtoList.forEach(System.out::println);
        }
    }
    // Öğrenci Ara
    public void search(String name){
        studentDtoList.stream().filter(temp ->temp.getName().equalsIgnoreCase(name))
                .forEach(System.out::println);
    }
    // Öğrenci Güncelle
    public void update(int id,StudentDto dto){
        for (StudentDto temp: studentDtoList){
            if (temp.getId() == id){
                temp.setName(dto.getName());
                temp.setSurname(dto.getSurname());
                temp.setBirthDate(dto.getBirthDate());
                temp.setGrade(dto.getGrade());
                System.out.println("Öğrenci Güncellendi");
                saveToFile();
                return;
            }
        }
        System.out.println(SpecialColor.RED + " Öğrenci Bulunamadı " + SpecialColor.RESET);
    }
    // Öğrenci Sil
    public void delete(int id,StudentDto dto){
        studentDtoList.removeIf(temp -> temp.getId() == id);
        System.out.println(SpecialColor.BLUE + " Öğrenci Silindi " + SpecialColor.RESET);
        // File Ekle
        saveToFile();

    }

    ////////////////////////////////////////////////////////////////
    // Toplam Öğrenci Sayısı
    // Rastgele Öğrenci
    // Öğrenci Not Ortalaması Hesapla
    // En Yüksek veya En Düşük Not Alan Öğrenci
    // Öğrenci Sıralaması (Doğum günü)

    ////////////////////////////////////////////////////////////////
    // Console Seçim (Öğrenci)


}
