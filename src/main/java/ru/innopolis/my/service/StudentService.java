package ru.innopolis.my.service;

import ru.innopolis.my.entity.Student;

import java.util.List;

public interface StudentService {

    Student getStudent(Long id);

    List<Student> getStudentList();

    void addStudent(Student student);

    void delStudent(Long id);


}
