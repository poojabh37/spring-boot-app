package io.javabrains.springbootstarter.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByTopicId(String topicId);
    //many to many --- implemented by spring data JPA,
    // findBy<mappingObjectName><mappingObjectPropertyName>

    List<Course> findByName(String name);
    //implemented by spring data JPA, findBy<PropertyName>

    List<Course> findByDescription(String description);

    @Query("select course from Course course where course.name= :courseName")
    List<Course> getByName(String courseName);

    Course findByIdAndTopicId(String id, String topicId);

    void deleteByIdAndTopicId(String id, String topicId);
}
