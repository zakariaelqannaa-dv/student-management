package StudentManagement.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StudentManagement.app.dao.StudentDAO;
import StudentManagement.app.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return studentDAO.findById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentDAO.save(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentDAO.update(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentDAO.delete(id);
    }
}