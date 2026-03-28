package StudentManagement.app.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StudentManagement.app.model.Course;

@Repository
public class CourseDAO {

    private final JdbcTemplate jdbcTemplate;

    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Course(
                rs.getInt("id"),
                rs.getString("course_name"),
                rs.getString("course_code"),
                rs.getInt("credits"),
                rs.getString("description")
            )
        );
    }

    public Course findById(int id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new Course(
                rs.getInt("id"),
                rs.getString("course_name"),
                rs.getString("course_code"),
                rs.getInt("credits"),
                rs.getString("description")
            ), id
        );
    }

    public void save(Course course) {
        String sql = "INSERT INTO courses(course_name, course_code, credits, description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            course.getCourseName(),
            course.getCourseCode(),
            course.getCredits() != null ? course.getCredits() : 0,
            course.getDescription()
        );
    }

    public void update(Course course) {
        String sql = "UPDATE courses SET course_name = ?, course_code = ?, credits = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            course.getCourseName(),
            course.getCourseCode(),
            course.getCredits() != null ? course.getCredits() : 0,
            course.getDescription(),
            course.getId()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
