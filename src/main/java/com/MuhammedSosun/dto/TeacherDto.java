package com.MuhammedSosun.dto;

import java.io.Serializable;

public record TeacherDto(
        PersonDto personDto,         //PersonDto üst ata sınıftan bilgiler bu şekilde alını Record la
        String subject,             //Uzmanlık alanı
        int yearsOfExperience,     //tecrübe yılı
        boolean isTenured,        //kadrolu mu değil mi (True,false)
        double salary            //maaş
) implements Serializable {

    public TeacherDto{
        if (personDto == null ) throw new IllegalArgumentException("Teacher içibde persondto boş geçilmez");
        if (subject == null||subject.isBlank() || subject.isEmpty()) throw new IllegalArgumentException("Uzmanlık alanını boş geçtiniz");
        if (yearsOfExperience < 0) throw new IllegalArgumentException("Deneyim yılınız 0 dan küçük girilmez");
        if (salary < 0) throw new IllegalArgumentException("Maaş negatif(-) girilmez");
    }
    public String fullName(){
        return personDto.getId() + " "+personDto.getName() + " " + personDto.getSurname();
    }
    public String experienceYear(){
        return (yearsOfExperience > 10) ? "Kıdemli Öğretmen":"Yeni öğretmen";
    }
}
