package com.example.myappJPA.services;

import com.example.myappJPA.models.HeadingWidget;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.HeadingWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class HeadingWidgetService {
    @Autowired
    HeadingWidgetRepository headingWidgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/headingWidget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return headingWidgetRepository.findById(wid).get();
    }

    @PutMapping("/api/headingWidget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid,@RequestBody HeadingWidget widget) {
        headingWidgetRepository.deleteById(wid);
        headingWidgetRepository.save(widget);
    }

    @DeleteMapping("/api/headingWidget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        headingWidgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/headingWidget")
    public void createWidget(@PathVariable("tid") int tid, @RequestBody HeadingWidget widget) {
        System.out.println("HERE>>>>>>>>>>>>>>>");
        System.out.println(widget.getHeadingText());
        System.out.println(widget.getSize());
        System.out.println(widget.getType());
        widget.setTopic(topicService.findTopicById(tid));
        System.out.println(widget.getHeadingText());
        headingWidgetRepository.save(widget);
    }

    @GetMapping("/api/headingWidget")
    public List<HeadingWidget> findAllWidgets() {
        return (List<HeadingWidget>) headingWidgetRepository.findAll();
    }
}
