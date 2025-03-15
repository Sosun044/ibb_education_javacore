package com.MuhammedSosun.controller;

import com.MuhammedSosun.Utils.SpecialColor;
import com.MuhammedSosun.dao.IDaoGenerics;
import com.MuhammedSosun.dao.TeacherDao;
import com.MuhammedSosun.dto.StudentDto;
import com.MuhammedSosun.dto.TeacherDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TeacherController implements IDaoGenerics<TeacherDto> {

    private final TeacherDao teacherDao;

    public TeacherController() {
        teacherDao = new TeacherDao();
    }

    @Override
    public Optional<TeacherDto> create(TeacherDto teacherDto) {
        if (teacherDto == null || teacherDao.findByid(teacherDto.id()).isPresent()){
            System.out.println(SpecialColor.RED +"Geçerli veya Mevcut olan öğrenciden dolayı eklenemez " + SpecialColor.RESET);
            return Optional.empty();
        }

        Optional<TeacherDto> createdTeacher = teacherDao.create(teacherDto);
        createdTeacher.ifPresentOrElse(
                temp -> System.out.println(SpecialColor.GREEN +"Başarılı Öğretmen Başarıyla eklendi" +
                        SpecialColor.RESET)
                ,() -> System.out.println("Bşarısız Öğretmen eklenmedi"));

        return createdTeacher;
    }

    @Override
    public Optional<TeacherDto> findByid(Integer id) {
        if (id == null || id<0){
            throw new IllegalArgumentException("Geçersiz ID girdiniz");
        }
        return teacherDao.findByid(id);
    }
    public Optional<TeacherDto> findByName(String name){
        if (name ==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Geçersiz İsim girdiniz");
        }
        return teacherDao.findByName(name);
    }

    @Override
    public List<TeacherDto> list() {
        List<TeacherDto> studentDtoList = Optional.of(teacherDao.list()).orElse(Collections.emptyList());
        if (studentDtoList.isEmpty()){
            System.out.println(SpecialColor.YELLOW + "Kayıtlı Öğretmen Listesi bulunamadı " + SpecialColor.RESET);
        }
        return teacherDao.list();
    }

    @Override
    public Optional<TeacherDto> update(int id, TeacherDto teacherDto) {
        if (id < 0 || teacherDto == null){
            throw new IllegalArgumentException("Güncelleme için geçerli Öğretmen bilgisi giriniz ");
        }
        return teacherDao.update(id,teacherDto);
    }

    @Override
    public Optional<TeacherDto> delete(int id) {
        if (id < 0){
            throw new IllegalArgumentException("Silmek için geçerli ID bilgisi giriniz ");
        }
        return teacherDao.delete(id);
    }

    @Override
    public void chooise() {
        teacherDao.chooise();
    }
}
