package com.MuhammedSosun.controller;

import com.MuhammedSosun.dao.IDaoGenerics;
import com.MuhammedSosun.dao.Studentdao;
import com.MuhammedSosun.dto.StudentDto;

import java.util.ArrayList;
import java.util.Optional;

public class StudentController implements IDaoGenerics<StudentDto> {

    private Studentdao studentdao;

    public StudentController() {
        studentdao = new Studentdao();
    }

    @Override
    public Optional<StudentDto> create(StudentDto studentDto) {
        return studentdao.create(studentDto);
    }

    @Override
    public Optional<StudentDto> findByid(Integer id) {
        return studentdao.findByid(id);
    }
    public Optional<StudentDto> findByName(String name){
        return studentdao.findByName(name);
    }

    @Override
    public ArrayList<StudentDto> list() {
        return studentdao.list();
    }

    @Override
    public Optional<StudentDto> update(int id, StudentDto studentDto) {
        return studentdao.update(id,studentDto);
    }

    @Override
    public Optional<StudentDto> delete(int id) {
        return studentdao.delete(id);
    }

    @Override
    public void chooise() {
        studentdao.chooise();
    }
}
