package com.MuhammedSosun.dto;

import com.MuhammedSosun.Utils.SpecialColor;
import com.MuhammedSosun.dao.EStudentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class StudentDto extends PersonDto implements Serializable {
    private static final long serialVersionUID= 354354354534534L;


    private Double midTerm;
    private Double finalTerm;
    private Double resultTerm = 0.0;
    private String status;
    private EStudentType eStudentType;

    static {
        System.out.println(SpecialColor.BLUE + "Static StudentDto Yüklendi" + SpecialColor.RESET);
    }
    public StudentDto(){
        super();
        this.midTerm= 0.0;
        this.finalTerm= 0.0;
        this.resultTerm = 0.0;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "midTerm=" + midTerm +
                ", finalTerm=" + finalTerm +
                ", resultTerm=" + resultTerm +
                ", status='" + status + '\'' +
                ", eStudentType=" + eStudentType +
                "} " + super.toString();
    }

    @Override
    void displayInfo() {

    }

    public StudentDto(Integer id,String name,String surname,LocalDate birthDate, Double midTerm, Double finalTerm, EStudentType eStudentType) {
       super(id,name,surname,birthDate);
        this.midTerm = midTerm;
        this.finalTerm = finalTerm;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
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
    public String determineStatus(){
        if (this.resultTerm == null ) return "Bilinmiyor";
        return (this.resultTerm >= 55) ? "Geçti" : "Kaldı";
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
        return resultTerm !=null ? resultTerm : 0.0;
    }
    public void setResultTerm(Double resultTerm) {
        if (resultTerm == null){
            this.resultTerm = 0.0;
        }
        this.resultTerm = resultTerm;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public EStudentType geteStudentType() {
        return eStudentType;
    }

    public void seteStudentType(EStudentType eStudentType) {
        this.eStudentType = eStudentType;
    }
}
