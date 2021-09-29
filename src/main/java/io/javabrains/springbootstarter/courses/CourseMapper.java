package io.javabrains.springbootstarter.courses;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseMapper {

    public List<CourseDTO> mapCourseToCourseDTO(List<Course> courses) {
        return courses
                .stream()
                .map(this::createCourseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO createCourseDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription());
        courseDTO.setTopicId(course.getTopic().getId());
        return courseDTO;
    }
}
