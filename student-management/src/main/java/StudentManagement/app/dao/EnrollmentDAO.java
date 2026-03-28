package StudentManagement.app.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StudentManagement.app.model.Course;
import StudentManagement.app.model.Enrollment;
import StudentManagement.app.model.Student;

@Repository
public class EnrollmentDAO {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Enrollment> findAll() {
        String sql = "SELECT * FROM enrollments";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Enrollment(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getDate("enrollment_date") != null ? rs.getDate("enrollment_date").toLocalDate() : null,
                rs.getString("status")
            )
        );
    }

    public List<Enrollment> findByStudentId(int studentId) {
        String sql = "SELECT * FROM enrollments WHERE student_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Enrollment(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getDate("enrollment_date") != null ? rs.getDate("enrollment_date").toLocalDate() : null,
                rs.getString("status")
            ), studentId
        );
    }

    public List<Enrollment> findByCourseId(int courseId) {
        String sql = "SELECT * FROM enrollments WHERE course_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Enrollment(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getDate("enrollment_date") != null ? rs.getDate("enrollment_date").toLocalDate() : null,
                rs.getString("status")
            ), courseId
        );
    }

    public Enrollment findByStudentIdAndCourseId(int studentId, int courseId) {
        String sql = "SELECT * FROM enrollments WHERE student_id = ? AND course_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Enrollment(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDate("enrollment_date") != null ? rs.getDate("enrollment_date").toLocalDate() : null,
                    rs.getString("status")
                ), studentId, courseId
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void enroll(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments(student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, studentId, courseId, Date.valueOf(LocalDate.now()), "ACTIVE");
    }

    public void unenroll(int studentId, int courseId) {
        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";
        jdbcTemplate.update(sql, studentId, courseId);
    }

    public void updateStatus(int enrollmentId, String status) {
        String sql = "UPDATE enrollments SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, enrollmentId);
    }

    public List<Student> findStudentsByCourseId(int courseId) {
        String sql = "SELECT s.* FROM students s " +
                     "INNER JOIN enrollments e ON s.id = e.student_id " +
                     "WHERE e.course_id = ? AND e.status = 'ACTIVE'";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
            ), courseId
        );
    }

    public List<Course> findCoursesByStudentId(int studentId) {
        String sql = "SELECT c.* FROM courses c " +
                     "INNER JOIN enrollments e ON c.id = e.course_id " +
                     "WHERE e.student_id = ? AND e.status = 'ACTIVE'";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Course(
                rs.getInt("id"),
                rs.getString("course_name"),
                rs.getString("course_code"),
                rs.getInt("credits"),
                rs.getString("description")
            ), studentId
        );
    }
}
