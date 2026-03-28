package StudentManagement.app.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSchedule {

    private Integer id;
    private Integer courseId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;

    public ClassSchedule() {
    }

    public ClassSchedule(Integer id, Integer courseId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String room) {
        this.id = id;
        this.courseId = courseId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("courseId")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @JsonProperty("dayOfWeek")
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @JsonProperty("startTime")
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("room")
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
