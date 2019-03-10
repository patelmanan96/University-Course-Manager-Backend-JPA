package com.example.myappJPA.services;

import com.example.myappJPA.models.Course;
import com.example.myappJPA.models.Lesson;
import com.example.myappJPA.models.Module;
import com.example.myappJPA.models.Topic;
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
    private CourseService courseService;

    @PostMapping("/api/lesson/{lid}/topic")
    public void createTopic(@PathVariable("lid") int lid, @RequestBody Topic topic) {
        System.out.println("CREATE TOPIC ADD HIT");
        lessonService.findLessonById(lid).getTopics().add(topic);
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid") int lid) {
        return lessonService.findLessonById(lid).getTopics();
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(@PathVariable("tid") int tid) {
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                for (Lesson l : m.getLessons()) {
                    for (Topic t : l.getTopics()) {
                        if (t.getId() == tid) {
                            return t;
                        }
                    }
                }
            }
        }
        return null;
    }

    @PutMapping("/api/topic/{tid}")
    public void updateTopic(@PathVariable("tid") int tid, @RequestBody Topic topic) {
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                for (Lesson l : m.getLessons()) {
                    for (Topic t : l.getTopics()) {
                        if (t.getId() == tid) {
                            t.setTitle(topic.getTitle());
                        }
                    }
                }
            }
        }
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(@PathVariable("tid") int tid) {
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                for (Lesson l : m.getLessons()) {
                    Iterator itr = l.getTopics().iterator();
                    while (itr.hasNext()) {
                        Topic x = (Topic) itr.next();
                        if (x.getId() == tid) {
                            itr.remove();
                        }
                    }
                }
            }
        }
    }
}
