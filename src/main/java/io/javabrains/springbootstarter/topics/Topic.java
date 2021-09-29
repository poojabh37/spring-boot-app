package io.javabrains.springbootstarter.topics;

import io.javabrains.springbootstarter.courses.Course;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "TOPIC_ID")
    private String id;

    @Column(name = "TOPIC_NAME")
    private String name;

    @Column(name = "TOPIC_DESCRIPTION")
    private String description;

    //TODO : check cascade type IMP
    @OneToMany(cascade = CascadeType.MERGE, fetch = LAZY, mappedBy = "topic",
            targetEntity = Course.class, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Topic() {
        //always have a no arg constructor, if having a parameterized constructor
    }

    public Topic(String id, String name, String description) {
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
