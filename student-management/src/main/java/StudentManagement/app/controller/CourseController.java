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

import StudentManagement.app.dao.ClassScheduleDAO;
import StudentManagement.app.dao.CourseDAO;
import StudentManagement.app.model.ClassSchedule;
import StudentManagement.app.model.Course;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseDAO courseDAO;
    private final ClassScheduleDAO classScheduleDAO;

    public CourseController(CourseDAO courseDAO, ClassScheduleDAO classScheduleDAO) {
        this.courseDAO = courseDAO;
        this.classScheduleDAO = classScheduleDAO;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseDAO.findById(id);
    }

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseDAO.save(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody Course course) {
        course.setId(id);
        courseDAO.update(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseDAO.delete(id);
    }

    @GetMapping("/{id}/schedule")
    public List<ClassSchedule> getScheduleByCourse(@PathVariable int id) {
        return classScheduleDAO.findByCourseId(id);
    }

    @PostMapping("/{id}/schedule")
    public void addScheduleToCourse(@PathVariable int id, @RequestBody ClassSchedule schedule) {
        schedule.setCourseId(id);
        classScheduleDAO.save(schedule);
    }

    @PutMapping("/schedule/{scheduleId}")
    public void updateSchedule(@PathVariable int scheduleId, @RequestBody ClassSchedule schedule) {
        schedule.setId(scheduleId);
        classScheduleDAO.update(schedule);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public void deleteSchedule(@PathVariable int scheduleId) {
        classScheduleDAO.delete(scheduleId);
    }
}
