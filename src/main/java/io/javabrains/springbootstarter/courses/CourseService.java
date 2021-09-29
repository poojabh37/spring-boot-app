package io.javabrains.springbootstarter.courses;

import io.javabrains.springbootstarter.topics.Topic;
import io.javabrains.springbootstarter.topics.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static io.javabrains.springbootstarter.topics.constants.ErrorConstants.TOPIC_DOES_NOT_EXISTS_ERROR_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicService topicService;

    public List<Course> getAll(String topicId) {
        List<Course> courses = courseRepository.findByTopicId(topicId);
        if (courses == null) {
            throw new ResponseStatusException(BAD_REQUEST, TOPIC_DOES_NOT_EXISTS_ERROR_MESSAGE);
        }
        return courses;
    }

    public Course getCourse(String topicId, String courseId) {
        return courseRepository.findByIdAndTopicId(courseId, topicId);
    }

    public void addCourse(String topicId, Course course) {
        Topic topic = topicService.get(topicId);
        course.setTopic(topic);
        courseRepository.save(course);
    }

    public Course update(String topicId, String courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findByIdAndTopicId(courseId, topicId);
        if(!StringUtils.isEmpty(courseDTO.getName())){
            course.setName(courseDTO.getName());
        }
        if(!StringUtils.isEmpty(courseDTO.getDescription())){
            course.setDescription(courseDTO.getDescription());
        }
        return courseRepository.save(course);
    }

    public void delete(String topicId, String courseId) {
        courseRepository.deleteByIdAndTopicId(courseId, topicId);
    }
}
