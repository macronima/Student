package ru.innopolis.my.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.my.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

/*
save
findOne
exists
findAll
count
delete
deleteAll
 */



}
