package com.MuhammedSosun.Project_step1_file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentDto implements Serializable {
    //serileştirme
    private static final long serialVersionUID= 354354354534534L;

    // Field
    private Integer id;
    private String name;
    private String surname;
    private Double midTerm;   //vize notu
    private Double finalTerm; //final notu
    private Double resultTerm;
    private LocalDate birthDate;
    private Date createdDate;

    // static (Nesne boyunca 1 kere oluşturulur)
    static {

    }
    //parametresiz constructor


    public StudentDto() {

    }

    public StudentDto(Integer id, String name, String surname, Double midTerm, Double finalTerm, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.midTerm = midTerm;
        this.finalTerm = finalTerm;
        this.birthDate = birthDate;
        this.resultTerm = calculateTerm();

    }

    private Double calculateTerm() {
        if (midTerm == null || finalTerm == null){
            return 0.0;
        }
        else {
            return ((midTerm*0.4) +(finalTerm*0.6));
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getMidTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = midTerm;
        this.resultTerm = calculateTerm();
    }

    public Double getFinalTerm() {
        return finalTerm;
    }

    public void setFinalTerm(Double finalTerm) {
        this.finalTerm = finalTerm;
        this.resultTerm = calculateTerm();
    }
}//end Student
