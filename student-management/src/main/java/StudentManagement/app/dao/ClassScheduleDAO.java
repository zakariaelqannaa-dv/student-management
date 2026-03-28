package StudentManagement.app.dao;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StudentManagement.app.model.ClassSchedule;

@Repository
public class ClassScheduleDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClassScheduleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassSchedule> findAll() {
        String sql = "SELECT * FROM class_schedule";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ClassSchedule(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getString("day_of_week"),
                rs.getTime("start_time") != null ? rs.getTime("start_time").toLocalTime() : null,
                rs.getTime("end_time") != null ? rs.getTime("end_time").toLocalTime() : null,
                rs.getString("room")
            )
        );
    }

    public List<ClassSchedule> findByCourseId(int courseId) {
        String sql = "SELECT * FROM class_schedule WHERE course_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ClassSchedule(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getString("day_of_week"),
                rs.getTime("start_time") != null ? rs.getTime("start_time").toLocalTime() : null,
                rs.getTime("end_time") != null ? rs.getTime("end_time").toLocalTime() : null,
                rs.getString("room")
            ), courseId
        );
    }

    public ClassSchedule findById(int id) {
        String sql = "SELECT * FROM class_schedule WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ClassSchedule(
                    rs.getInt("id"),
                    rs.getInt("course_id"),
                    rs.getString("day_of_week"),
                    rs.getTime("start_time") != null ? rs.getTime("start_time").toLocalTime() : null,
                    rs.getTime("end_time") != null ? rs.getTime("end_time").toLocalTime() : null,
                    rs.getString("room")
                ), id
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void save(ClassSchedule schedule) {
        String sql = "INSERT INTO class_schedule(course_id, day_of_week, start_time, end_time, room) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            schedule.getCourseId() != null ? schedule.getCourseId() : 0,
            schedule.getDayOfWeek(),
            schedule.getStartTime() != null ? Time.valueOf(schedule.getStartTime()) : null,
            schedule.getEndTime() != null ? Time.valueOf(schedule.getEndTime()) : null,
            schedule.getRoom()
        );
    }

    public void update(ClassSchedule schedule) {
        String sql = "UPDATE class_schedule SET course_id = ?, day_of_week = ?, start_time = ?, end_time = ?, room = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            schedule.getCourseId() != null ? schedule.getCourseId() : 0,
            schedule.getDayOfWeek(),
            schedule.getStartTime() != null ? Time.valueOf(schedule.getStartTime()) : null,
            schedule.getEndTime() != null ? Time.valueOf(schedule.getEndTime()) : null,
            schedule.getRoom(),
            schedule.getId()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM class_schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteByCourseId(int courseId) {
        String sql = "DELETE FROM class_schedule WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId);
    }
}
