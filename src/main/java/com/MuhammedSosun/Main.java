package com.MuhammedSosun;

import com.MuhammedSosun.controller.StudentController;

public class Main {
    public static void main(String[] args) {
        try {
            StudentController studentController = new StudentController();
            studentController.chooise();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    }
