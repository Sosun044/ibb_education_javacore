package com.MuhammedSosun.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(){
        super("Kayıt Bulunamadı");
    }
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
