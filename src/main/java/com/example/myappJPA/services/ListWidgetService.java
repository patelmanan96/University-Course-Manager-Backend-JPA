package com.example.myappJPA.services;

import com.example.myappJPA.models.ImageWidget;
import com.example.myappJPA.models.ListWidget;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.ImageWidgetRepository;
import com.example.myappJPA.repositories.ListWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListWidgetService {

    @Autowired
    ListWidgetRepository listWidgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/listWidget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return listWidgetRepository.findById(wid).get();
    }

    @PutMapping("/api/listWidget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid,@RequestBody ListWidget widget) {
        listWidgetRepository.deleteById(wid);
        listWidgetRepository.save(widget);
    }

    @DeleteMapping("/api/listWidget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        listWidgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/listWidget")
    public void createWidget(@PathVariable("tid") int tid, @RequestBody ListWidget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        listWidgetRepository.save(widget);
    }

    @GetMapping("/api/topic/listWidget")
    public List<ListWidget> findAllWidgets() {
        return (List<ListWidget>) listWidgetRepository.findAll();
    }
}
