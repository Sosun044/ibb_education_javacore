package com.MuhammedSosun.exception;

public class RegisterNotFoundException extends RuntimeException{
    public RegisterNotFoundException(){
        super("Kayıt Bulunamadı");
    }
    public RegisterNotFoundException(String message) {
        super(message);
    }
}
