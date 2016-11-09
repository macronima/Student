package ru.innopolis.my.dao;

import ru.innopolis.my.entity.Student;

import java.util.List;

public interface StudentDAO {

    Student getStudent(Integer id);

    List<Student> getStudentList();

    void addStudent(Student student);

    void delStudent(Integer id);

}
