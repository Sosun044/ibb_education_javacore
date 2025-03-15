package com.MuhammedSosun.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(){
        super("Kayıt Bulunamadı");
    }
    public StudentNotFoundException(String message) {
        super(message);
    }
}
