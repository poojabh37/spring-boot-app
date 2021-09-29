package io.javabrains.springbootstarter.courses;

import io.javabrains.springbootstarter.topics.Topic;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @Column(name = "COURSE_ID")
    private String id;

    @Column(name = "COURSE_NAME")
    private String name;

    @Column(name = "COURSE_DESCRIPTION")
    private String description;

    @ManyToOne(fetch = EAGER, targetEntity = Topic.class)
    @JoinColumn(name = "TOPIC_ID", referencedColumnName = "TOPIC_ID", nullable = false)
    private Topic topic;

    public Course() {
    }

    public Course(String id, String name, String description) {
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
