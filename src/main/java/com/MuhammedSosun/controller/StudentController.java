package com.MuhammedSosun.controller;

import com.MuhammedSosun.Utils.SpecialColor;
import com.MuhammedSosun.dao.IDaoGenerics;
import com.MuhammedSosun.dao.Studentdao;
import com.MuhammedSosun.dto.StudentDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentController implements IDaoGenerics<StudentDto> {

    private Studentdao studentdao;

    public StudentController() {
        studentdao = new Studentdao();
    }

    @Override
    public Optional<StudentDto> create(StudentDto studentDto) {

        if (studentDto == null || studentdao.findByid(studentDto.getId()).isPresent()){
            System.out.println(SpecialColor.RED +"Geçerli veya Mevcut olan öğrenciden dolayı eklenemez " + SpecialColor.RESET);
            return Optional.empty();
        }

        Optional<StudentDto> createdStudent = studentdao.create(studentDto);
        createdStudent.ifPresentOrElse(
                temp -> System.out.println(SpecialColor.GREEN +"Başarılı Öğrenci Başarıyla eklendi" +
                        SpecialColor.RESET)
                ,() -> System.out.println("Bşarısız Öğrenci eklenmedi"));

        return createdStudent;
    }

    @Override
    public Optional<StudentDto> findByid(Integer id) {
        if (id == null || id<0){
            throw new IllegalArgumentException("Geçersiz ID girdiniz");
        }
        return studentdao.findByid(id);
    }
    public Optional<StudentDto> findByName(String name){
        if (name ==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Geçersiz İsim girdiniz");
        }
        return studentdao.findByName(name.trim());
    }

    @Override
    public List<StudentDto> list() {
        List<StudentDto> studentDtoList = Optional.of(studentdao.list()).orElse(Collections.emptyList());
        if (studentDtoList.isEmpty()){
            System.out.println(SpecialColor.YELLOW + "Kayıtlı öğrenci Listesi bulunamadı " + SpecialColor.RESET);
        }
        return studentDtoList;
    }

    @Override
    public Optional<StudentDto> update(int id, StudentDto studentDto) {
        if (id < 0 || studentDto == null){
            throw new IllegalArgumentException("Güncelleme için geçerli öğrenci bilgisi giriniz ");
        }
        return studentdao.update(id,studentDto);
    }

    @Override
    public Optional<StudentDto> delete(int id) {
        if (id < 0){
            throw new IllegalArgumentException("Silmek için geçerli ID bilgisi giriniz ");
        }
        return studentdao.delete(id);
    }

    @Override
    public void chooise() {
        studentdao.chooise();
    }
}
