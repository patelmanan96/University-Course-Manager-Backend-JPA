package com.example.myappJPA.services;

import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WidgetService {
    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return widgetRepository.findById(wid).get();
    }

    @PutMapping("/api/widget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid,@RequestBody
            Widget widget) {
        widgetRepository.deleteById(wid);
        widgetRepository.save(widget);
    }

    @DeleteMapping("/api/widget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        widgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/widget")
    public void createWidget(@PathVariable("tid") int tid,@RequestBody Widget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        System.out.println("WT : "+ widget.getWidgetType());
        System.out.println(widget.getTopic().getTitle());
        System.out.println(widget.getId());
        widgetRepository.save(widget);
    }

    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgets(@PathVariable("tid") int tid) {
        return (List<Widget>) widgetRepository.findAll();
    }

}