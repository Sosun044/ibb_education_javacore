package com.MuhammedSosun.controller;

import com.MuhammedSosun.Utils.SpecialColor;
import com.MuhammedSosun.dao.IDaoGenerics;
import com.MuhammedSosun.dao.TeacherDao;
import com.MuhammedSosun.dto.TeacherDto;

import java.util.List;

public class TeacherController implements IDaoGenerics<TeacherDto> {

    private final TeacherDao teacherDao;

    public TeacherController() {
        teacherDao = new TeacherDao();
    }

    @Override
    public TeacherDto create(TeacherDto teacherDto) {
        TeacherDto createdTeacher =teacherDao.create(teacherDto);
        if (createdTeacher == null){
            System.out.println(SpecialColor.RED + " Öğretmen Olusturulamadı Geçerli bilgileri giriniz" + SpecialColor.RESET);

        }
        return createdTeacher;
    }

    @Override
    public TeacherDto findByid(Integer id) {
        return teacherDao.findByid(id);
    }
    public TeacherDto findByName(String name){
        return teacherDao.findByName(name);
    }

    @Override
    public List<TeacherDto> list() {
        return teacherDao.list();
    }

    @Override
    public TeacherDto update(int id, TeacherDto teacherDto) {
        return teacherDao.update(id,teacherDto);
    }

    @Override
    public TeacherDto delete(int id) {
        return teacherDao.delete(id);
    }

    @Override
    public void chooise() {
        teacherDao.chooise();
    }
}
