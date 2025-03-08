package com.MuhammedSosun.dto;

import com.MuhammedSosun.dao.ETeacherSubject;

import java.io.Serializable;
import java.time.LocalDate;

public record TeacherDto(
        Integer id,
        String name,
        String surname,
        LocalDate birthDate,//PersonDto üst ata sınıftan bilgiler bu şekilde alını Record la
        //String subject,             //Uzmanlık alanı
        ETeacherSubject eTeacherSubject,
        int yearsOfExperience,     //tecrübe yılı
        boolean isTenured,        //kadrolu mu değil mi (True,false)
        double salary            //maaş
) implements Serializable {

    public TeacherDto{
        if (id == null || id < 0) throw new IllegalArgumentException("ID negatif olamaz");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("İsim boş olamaz");
        if (surname == null || surname.isBlank()) throw new IllegalArgumentException("Soyisim boş olamaz");
        if (eTeacherSubject == null) throw new IllegalArgumentException("Uzmanlık alanını boş geçtiniz");
        if (yearsOfExperience < 0) throw new IllegalArgumentException("Deneyim yılınız 0 dan küçük girilmez");
        if (salary < 0) throw new IllegalArgumentException("Maaş negatif(-) girilmez");
    }
    public String fullName(){
        return id+ " " + name + " " + surname + " " +salary+" "+ yearsOfExperience;
    }
    public String experienceYear(){
        return (yearsOfExperience > 10) ? "Kıdemli Öğretmen":"Yeni öğretmen";
    }
}
