package com.example.myappJPA.services;

import com.example.myappJPA.models.Lesson;
import com.example.myappJPA.models.Topic;
import com.example.myappJPA.models.Widget;
import com.example.myappJPA.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "*")
public class LessonService {
    @Autowired
    private TopicService topicService;

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping("/api/module/{mid}/lesson")
    public void createLesson(@PathVariable("mid") int mid, @RequestBody Lesson lesson) {
        lesson.setModule(moduleService.findModuleById(mid));
        lessonRepository.save(lesson);
    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid") int mid) {
        return lessonRepository.findByModuleId(mid);
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lid) {
        return lessonRepository.findById(lid).get();
    }

    @PutMapping("/api/lesson/{lid}")
    public void updateLesson(@PathVariable("lid") int lid, @RequestBody Lesson lesson) {
        Lesson l = lessonRepository.findById(lid).get();
        l.setTitle(lesson.getTitle());
        lessonRepository.save(l);
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(@PathVariable("lid") int lid) {
        for (Topic t : topicService.findAllTopics(lid)) {
            for (Widget w : widgetService.findAllWidgets(t.getId())) {
                widgetService.deleteWidget(w.getId());
            }
            topicService.deleteTopic(t.getId());
        }
        lessonRepository.deleteById(lid);
    }
}
