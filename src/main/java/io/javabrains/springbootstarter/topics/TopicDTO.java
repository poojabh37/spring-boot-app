package io.javabrains.springbootstarter.topics;

import io.javabrains.springbootstarter.courses.CourseDTO;

import java.util.List;

public class TopicDTO {

    private String id;
    private String name;
    private String description;
    private List<CourseDTO> courses;

    public TopicDTO() {
    }

    public TopicDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
