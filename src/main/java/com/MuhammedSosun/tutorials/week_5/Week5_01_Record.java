package com.MuhammedSosun.tutorials.week_5;

import com.MuhammedSosun.dao.EStudentType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public record Week5_01_Record(
        Integer id,
        String name,
        String surname,
        EStudentType eStudentType,
        Double mid,
        Double finalTerm,
        Double resultTerm,
        LocalDate birthDate,
        Date createdDate
)implements Serializable {
    public  static final long serialVersionUID = 2345235235235235L;

    public Week5_01_Record(Integer id,String name,String surname,EStudentType eStudentType,Double mid,Double finalTerm,LocalDate birthDate){
        this(id,name,surname,eStudentType,mid,finalTerm,calculateResult(mid,finalTerm),birthDate,new Date(System.currentTimeMillis()));
    }

    private static Double calculateResult(Double mid, Double finalTerm) {
        if (mid == null || finalTerm == null) return 0.0;
        return (mid * 0.4 + finalTerm * 0.6);
    }
}