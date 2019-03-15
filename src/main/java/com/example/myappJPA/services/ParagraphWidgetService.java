package com.example.myappJPA.services;

import com.example.myappJPA.models.ListWidget;
import com.example.myappJPA.models.ParagraphWidget;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.ParagraphWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")

public class ParagraphWidgetService {
    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/paragraphWidget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int wid) {
        return paragraphWidgetRepository.findById(wid).get();
    }

    @PutMapping("/api/paragraphWidget/{wid}")
    public void updateWidget(@PathVariable("wid") int wid,@RequestBody ParagraphWidget widget) {
        paragraphWidgetRepository.deleteById(wid);
        paragraphWidgetRepository.save(widget);
    }

    @DeleteMapping("/api/paragraphWidget/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        paragraphWidgetRepository.deleteById(wid);
    }

    @PostMapping("/api/topic/{tid}/paragraphWidget")
    public void createWidget(@PathVariable("tid") int tid, @RequestBody ParagraphWidget widget) {
        widget.setTopic(topicService.findTopicById(tid));
        paragraphWidgetRepository.save(widget);
    }

    @GetMapping("/api/paragraphWidget")
    public List<ParagraphWidget> findAllWidgets() {
        return (List<ParagraphWidget>) paragraphWidgetRepository.findAll();
    }
}
