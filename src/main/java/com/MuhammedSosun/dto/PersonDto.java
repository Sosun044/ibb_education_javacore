package com.MuhammedSosun.dto;

import java.time.LocalDate;
import java.util.Date;

abstract public class PersonDto {
    protected Integer id;
    protected String name;
    protected String surname;
    protected String emailAddress;
    protected String password;
    protected LocalDate birthDate;
    protected Date createDate;

    //AES ENCRYPTED
    private static final String AES_ALGORITHM ="AES";
    private static final String MY_SECRET_KEY ="MY_SECRET_AES_KEY"; // GERÇEK PROJELERDE DİKKAT GÜVENLİ OLMASI GEREKİYOR

    public PersonDto() {
        this.id = 0;
        this.name = "name unknow";
        this.surname = "surname unknow";
        this.emailAddress = "your_email address";
        this.password = "Your_password";
        this.birthDate = LocalDate.now();
        this.createDate = new Date(System.currentTimeMillis());
    }

    public PersonDto(Integer id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.createDate = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", createDate=" + createDate +
                '}';
    }
    abstract void displayInfo();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
