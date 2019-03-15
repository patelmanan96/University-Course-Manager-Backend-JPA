package com.example.myappJPA.services;

import com.example.myappJPA.models.ImageWidget;
import com.example.myappJPA.models.LinkWidget;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.LinkWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")

public class LinkWidgetService {

    @Autowired
    LinkWidgetRepository linkWidgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/linkWidget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return linkWidgetRepository.findById(wid).get();
    }

    @PutMapping("/api/linkWidget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid, @RequestBody LinkWidget widget) {
        linkWidgetRepository.deleteById(wid);
        linkWidgetRepository.save(widget);
    }

    @DeleteMapping("/api/linkWidget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        linkWidgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/linkWidget")
    public void createWidget(@PathVariable("tid") int tid, @RequestBody LinkWidget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        linkWidgetRepository.save(widget);
    }

    @GetMapping("/api/linkWidget")
    public List<LinkWidget> findAllWidgets() {
        return (List<LinkWidget>) linkWidgetRepository.findAll();
    }
}
