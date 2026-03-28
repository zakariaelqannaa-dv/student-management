package StudentManagement.app.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StudentManagement.app.model.Student;

@Repository
public class StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
            )
        );
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
            ), id
        );
    }

    public void save(Student student) {
        String sql = "INSERT INTO students(first_name, last_name, email) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
            student.getFirstName(),
            student.getLastName(),
            student.getEmail()
        );
    }

    public void update(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

        jdbcTemplate.update(sql,
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getId()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}