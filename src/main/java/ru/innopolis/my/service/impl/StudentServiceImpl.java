package ru.innopolis.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.my.entity.Student;
import ru.innopolis.my.repository.StudentRepository;
import ru.innopolis.my.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository sR;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {return sR.findOne(id);}

    @Override
    public List<Student> getStudentList() {return (List)sR.findAll();}

    @Override
    public void addStudent(Student student){sR.save(student);}

    @Override
    public void delStudent(Long id){sR.delete(id);}
}
