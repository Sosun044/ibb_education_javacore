package com.MuhammedSosun.tutorials._week_3;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lombok {
    private Long id;
    private String name;
    private String surname;
    private Date createdDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname != null && surname.length() > 3){
            this.surname = surname.substring(0,3).toUpperCase() + "*".repeat(surname.length() - 3);
        }
        else if (surname != null){
            this.surname = surname.toLowerCase();
        }
        else {
            this.surname="";
        }

    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static void main(String[] args) {
        Lombok bean=new Lombok();
        bean.setId(1L);
        bean.setName("Muhammed");
        bean.setSurname("Sosun");
        System.out.println(bean);



        /*
        Lombok lombok = Lombok.builder()
                .id(1L).name("Muhammed").surname("Sosun").createdDate(new Date(System.currentTimeMillis())).
                build();
        System.out.println(lombok);

         */
    }
}
