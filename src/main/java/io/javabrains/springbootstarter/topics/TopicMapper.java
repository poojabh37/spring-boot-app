package io.javabrains.springbootstarter.topics;

import io.javabrains.springbootstarter.courses.CourseDTO;
import io.javabrains.springbootstarter.courses.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicMapper {

    @Autowired
    private CourseMapper courseMapper;

    public List<TopicDTO> mapTopicToTopicDTO(List<Topic> topics) {
        return topics
                .stream()
                .map(this::createTopicDTO)
                .collect(Collectors.toList());
    }

    public TopicDTO createTopicDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO(topic.getId(), topic.getName(), topic.getDescription());
        List<CourseDTO> courseDTOs = courseMapper.mapCourseToCourseDTO(topic.getCourses());
        topicDTO.setCourses(courseDTOs);
        return topicDTO;
    }

}
