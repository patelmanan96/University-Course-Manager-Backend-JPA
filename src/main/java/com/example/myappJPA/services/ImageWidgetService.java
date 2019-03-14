package com.example.myappJPA.services;

import com.example.myappJPA.models.HeadingWidget;
import com.example.myappJPA.models.ImageWidget;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.HeadingWidgetRepository;
import com.example.myappJPA.repositories.ImageWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageWidgetService {
    @Autowired
    ImageWidgetRepository imageWidgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/imageWidget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return imageWidgetRepository.findById(wid).get();
    }

    @PutMapping("/api/imageWidget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid, @RequestBody ImageWidget widget) {
        imageWidgetRepository.deleteById(wid);
        imageWidgetRepository.save(widget);
    }

    @DeleteMapping("/api/imageWidget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        imageWidgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/imageWidget")
    public void createWidget(@PathVariable("tid") int tid, @RequestBody ImageWidget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        imageWidgetRepository.save(widget);
    }

    @GetMapping("/api/topic/imageWidget")
    public List<ImageWidget> findAllWidgets() {
        return (List<ImageWidget>) imageWidgetRepository.findAll();
    }
}
