package io.javabrains.springbootstarter.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicMapper topicMapper;

    @GetMapping(value = "/topics",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicDTO> getAllTopics() {
        List<Topic> topics = topicService.getAll();
        return topicMapper.mapTopicToTopicDTO(topics);
    }

    @GetMapping(value = "/topics/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDTO getTopic(@PathVariable("id") String id) {
        Topic topic = topicService.get(id);
        return topicMapper.createTopicDTO(topic);
    }

    //by default, RequestMethod.GET is applied
    @RequestMapping(value = "/topics",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTopic(@RequestBody Topic topic) {
        Topic savedTopic = topicService.save(topic);
        // For POST request, 201 is appropriate with location response header
        // /topics{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/topics/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDTO updateTopic(@PathVariable("id") String id, @RequestBody TopicDTO topicDTO) {
        Topic topic = topicService.update(id, topicDTO);
        return topicMapper.createTopicDTO(topic);
    }

    @DeleteMapping(value = "/topics/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteTopic(@PathVariable("id") String topicId) {
        topicService.delete(topicId);
        return ResponseEntity.noContent().build();
    }
}
