package StudentManagement.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {

    private Integer id;
    private String courseName;
    private String courseCode;
    private Integer credits;
    private String description;

    public Course() {
    }

    public Course(Integer id, String courseName, String courseCode, Integer credits, String description) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("courseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @JsonProperty("courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @JsonProperty("credits")
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
