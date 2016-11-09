package ru.innopolis.my.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.my.dao.StudentDAO;
import ru.innopolis.my.entity.Student;

import javax.sql.DataSource;
import java.util.List;

public class StudentDBDAO implements StudentDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){this.jdbcTemplate=new JdbcTemplate(dataSource);}

    private RowMapper<Student> getStudentRowMapper() {
        return (rs, rowNum) -> {
            Student std = new Student() ;
            std.setId(rs.getInt("id"));
            std.setSurname(rs.getString("surname"));
            std.setName(rs.getString("name"));
            std.setGender(rs.getString("gender"));
            std.setBirthday(rs.getDate("birthday"));
            return std;};
    }

    @Override
    public Student getStudent(Integer id){
        String query = "SELECT s.* FROM student s WHERE s.id = ?;";
        Student student = this.jdbcTemplate.queryForObject(query, new Object[]{id},getStudentRowMapper());
        return student;
    }

    @Override
    public List<Student> getStudentList(){
        String query = "SELECT s.* FROM student s;";
        List<Student> students = this.jdbcTemplate.query(query,getStudentRowMapper());
        return students;
    }

    @Override
    public void addStudent(Student std){
        if (std.getId() != null) {
            this.jdbcTemplate.update(
                    "UPDATE students SET surname=?,name=?,gender=?,birthday=? WHERE id = ?",
                    std.getSurname(), std.getName(), std.getGender(), std.getBirthday(), std.getId());
        } else {
            this.jdbcTemplate.update("INSERT INTO students (surname, name,gender,birthday) VALUES (?,?,?,?)",
                    std.getSurname(), std.getName(), std.getGender(), std.getBirthday());
        }
    }

    @Override
    public void delStudent(Integer id) {
        this.jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }


}