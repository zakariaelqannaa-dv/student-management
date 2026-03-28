package StudentManagement.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StudentManagement.app.dao.EnrollmentDAO;
import StudentManagement.app.model.Course;
import StudentManagement.app.model.Enrollment;
import StudentManagement.app.model.Student;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentController(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable int studentId) {
        return enrollmentDAO.findByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathVariable int courseId) {
        return enrollmentDAO.findByCourseId(courseId);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public void enrollStudent(@PathVariable int studentId, @PathVariable int courseId) {
        enrollmentDAO.enroll(studentId, courseId);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public void unenrollStudent(@PathVariable int studentId, @PathVariable int courseId) {
        enrollmentDAO.unenroll(studentId, courseId);
    }

    @GetMapping("/course/{courseId}/students")
    public List<Student> getStudentsByCourse(@PathVariable int courseId) {
        return enrollmentDAO.findStudentsByCourseId(courseId);
    }

    @GetMapping("/student/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable int studentId) {
        return enrollmentDAO.findCoursesByStudentId(studentId);
    }
}
