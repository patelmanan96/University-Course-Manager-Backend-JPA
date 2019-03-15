package com.example.myappJPA.services;

import com.example.myappJPA.models.*;
import com.example.myappJPA.models.Module;
import com.example.myappJPA.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class ModuleService {
    @Autowired
    private TopicService topicService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModuleRepository moduleRepository;

    @PostMapping("/api/courses/{cid}/modules")
    public void createModule(@PathVariable("cid") int courseId, @RequestBody Module module) {
        //moduleRepository.save(module);
        //System.out.println("BEFORE : "+courseService.findCourseById(courseId).getModules().size());
        //courseService.findCourseById(courseId).getModules().add(module);
        Course c = courseService.findCourseById(courseId);
        module.setCourse(c);
        moduleRepository.save(module);
    }

    @GetMapping("/api/courses/{cid}/modules")
    public List<Module> findAllModules(@PathVariable("cid") int courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(@PathVariable("mid") int mid) {
        return moduleRepository.findById(mid).get();
    }

    @PutMapping("/api/modules/{mid}")
    public void updateModule(@PathVariable("mid") int mid, @RequestBody Module module) {
        Module r = moduleRepository.findById(mid).get();
        r.setTitle(module.getTitle());
        moduleRepository.save(r);
    }

    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(@PathVariable("mid") int mid) {
        for(Lesson l : lessonService.findAllLessons(mid)){
            for(Topic t: topicService.findAllTopics(l.getId())){
                for(Widget w : widgetService.findAllWidgets(t.getId())){
                    widgetService.deleteWidget(w.getId());
                }
                topicService.deleteTopic(t.getId());
            }
            lessonService.deleteLesson(l.getId());
        }
        moduleRepository.deleteById(mid);
    }
}
