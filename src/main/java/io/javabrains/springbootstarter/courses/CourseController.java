package io.javabrains.springbootstarter.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/topics/{topicId}/courses")
    public List<CourseDTO> getAllCourses(@PathVariable String topicId) {
        List<Course> courses = courseService.getAll(topicId);
        return courseMapper.mapCourseToCourseDTO(courses);
    }

    @GetMapping("/topics/{topicId}/courses/{courseId}")
    public CourseDTO getCourse(@PathVariable String topicId, @PathVariable String courseId) {
        Course course = courseService.getCourse(topicId, courseId);
        return courseMapper.createCourseDTO(course);
    }

    @PostMapping(value = "/topics/{topicId}/courses",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
        courseService.addCourse(topicId, course);
    }

    @PutMapping(value = "/topics/{topicId}/courses/{courseId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO updateCourse(@PathVariable String topicId, @PathVariable String courseId,
                                  @RequestBody CourseDTO courseDTO) {
        Course updated = courseService.update(topicId, courseId, courseDTO);
        return courseMapper.createCourseDTO(updated);
    }

    @DeleteMapping(value = "/topics/{topicId}/courses/{courseId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCourse(@PathVariable String topicId, @PathVariable String courseId) {
        courseService.delete(topicId, courseId);
    }
}
