package com.example.myappJPA.services;

import com.example.myappJPA.models.Course;
import com.example.myappJPA.models.Module;
import com.example.myappJPA.models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class LessonService {
    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/module/{mid}/lesson")
    public void createLesson(@PathVariable("mid") int mid, @RequestBody Lesson lesson) {
        moduleService.findModuleById(mid).getLessons().add(lesson);
    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid") int mid) {
        return moduleService.findModuleById(mid).getLessons();
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lid) {
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                for (Lesson l : m.getLessons()) {
                    if (l.getId() == lid) {
                        return l;
                    }
                }
            }
        }
        return null;
    }

    @PutMapping("/api/lesson/{lid}")
    public void updateLesson(@PathVariable("lid") int lid, @RequestBody Lesson lesson) {
        System.out.println("L TITLE UPDATED : "+lesson.getTitle());
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                for (Lesson l : m.getLessons()) {
                    if (l.getId() == lid) {
                        l.setTitle(lesson.getTitle());
                        l.setTopics(lesson.getTopics());
                    }
                }
            }
        }
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(@PathVariable("lid") int lid) {
        for (Course i : courseService.findAllCourses()) {
            for (Module m : i.getModules()) {
                    Iterator itr = m.getLessons().iterator();
                    while (itr.hasNext())
                    {
                        Lesson x = (Lesson)itr.next();
                        if (x.getId() == lid){
                            itr.remove();
                    }
                }
            }
        }
    }
}
