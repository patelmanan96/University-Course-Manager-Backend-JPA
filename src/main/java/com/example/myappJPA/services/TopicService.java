package com.example.myappJPA.services;

import com.example.myappJPA.models.*;
import com.example.myappJPA.models.Module;
import com.example.myappJPA.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class TopicService {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping("/api/lesson/{lid}/topic")
    public void createTopic(@PathVariable("lid") int lid, @RequestBody Topic topic) {
        topic.setLesson(lessonService.findLessonById(lid));
        topicRepository.save(topic);
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid") int lid) {
        return topicRepository.findByLessonId(lid);
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(@PathVariable("tid") int tid) {
        return topicRepository.findById(tid).get();
    }

    @PutMapping("/api/topic/{tid}")
    public void updateTopic(@PathVariable("tid") int tid, @RequestBody Topic topic) {
        Topic t = topicRepository.findById(tid).get();
        t.setTitle(topic.getTitle());
        topicRepository.save(t);
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(@PathVariable("tid") int tid) {
        topicRepository.deleteById(tid);
    }

/*
    @PostMapping("/api/topic/{tid}/widget")
    public void createWidget(@PathVariable("tid") int tid, Widget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        widgetRepository.save(widget);
    }

    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgets(@PathVariable("tid") int tid, Widget widget) {
        return (List<Widget>) widgetRepository.findAll();
    }*/

}
