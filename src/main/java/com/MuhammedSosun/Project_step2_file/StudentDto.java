package com.MuhammedSosun.Project_step2_file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class StudentDto implements Serializable {
    private static final long serialVersionUID= 354354354534534L;

    private Integer id;
    private String name;
    private String surname;
    private Double midTerm;
    private Double finalTerm;
    private Double resultTerm;
    private LocalDate birthDate;
    private Date createDate;
    private EStudentType eStudentType;
    static {}
    public StudentDto(){
    }

    public StudentDto(Integer id, String name, String surname, Double midTerm, Double finalTerm,LocalDate birthDate,EStudentType eStudentType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.midTerm = midTerm;
        this.finalTerm = finalTerm;
        this.resultTerm = calculateResult();
        this.birthDate = birthDate;
        this.createDate = new Date(System.currentTimeMillis());
        this.eStudentType = eStudentType;

    }

    private Double calculateResult() {
        if (midTerm == null || finalTerm == null){
            return 0.0;
        }
        else {
            return ((midTerm * 0.4) + (finalTerm * 0.6));
        }
    }

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

    public Double getMidTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = midTerm;
        this.resultTerm = calculateResult();
    }

    public Double getFinalTerm() {
        return finalTerm;
    }

    public void setFinalTerm(Double finalTerm) {
        this.finalTerm = finalTerm;
        this.resultTerm = calculateResult();
    }

    public Double getResultTerm() {
        return resultTerm;
    }

    public void setResultTerm(Double resultTerm) {
        this.resultTerm = resultTerm;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EStudentType geteStudentType() {
        return eStudentType;
    }

    public void seteStudentType(EStudentType eStudentType) {
        this.eStudentType = eStudentType;
    }
}
