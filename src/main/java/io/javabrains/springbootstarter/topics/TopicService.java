package io.javabrains.springbootstarter.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public Topic get(String id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("id: " + id));
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic update(String topicId, TopicDTO topicDTO) {
        Topic topic = get(topicId);
        if (!StringUtils.isEmpty(topicDTO.getName())) {
            topic.setName(topicDTO.getName());
        }
        if (!StringUtils.isEmpty(topicDTO.getDescription())) {
            topic.setDescription(topicDTO.getDescription());
        }
        return topicRepository.save(topic);
    }

    public void delete(String topicId) {
        topicRepository.deleteById(topicId);
    }

}
