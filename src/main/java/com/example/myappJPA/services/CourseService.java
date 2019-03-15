package com.example.myappJPA.services;

import com.example.myappJPA.models.Module;
import com.example.myappJPA.repositories.CourseRepository;
import com.example.myappJPA.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/api/courses")
    public void createCourse(@RequestBody Course course){
        courseRepository.save(course);
    }

    @GetMapping("/api/courses")
    public List<Course> findAllCourses(){
        return (List<Course>)courseRepository.findAll();
    }

    @GetMapping("/api/courses/{cid}")
    public Course findCourseById(@PathVariable("cid") int cid){
        Course c = courseRepository.findById(cid).get();
        if(c != null && c.getTitle() != null){
            return c;
        }
        return new Course();
    }

    @PutMapping("/api/courses/{cid}")
    public void updateCourse(@PathVariable("cid") int cid, @RequestBody Course course){
        Course c = courseRepository.findById(cid).get();
        c.setTitle(course.getTitle());
        courseRepository.save(c);
    }

    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(@PathVariable("cid") int cid){
        for(Module m : moduleService.findAllModules(cid)){
            for(Lesson l : lessonService.findAllLessons(m.getId())){
                for(Topic t: topicService.findAllTopics(l.getId())){
                    for(Widget w : widgetService.findAllWidgets(t.getId())){
                        widgetService.deleteWidget(w.getId());
                    }
                    topicService.deleteTopic(t.getId());
                }
                lessonService.deleteLesson(l.getId());
            }
            moduleService.deleteModule(m.getId());
        }
        courseRepository.deleteById(cid);
    }

    @GetMapping("/api/courses/sessionCourses")
    public List<Course> findCoursesForSessionUser(HttpSession session){
        String username = ((User)session.getAttribute("currentUser")).getUsername();
        //System.out.println("U NAME: " + username);
        return courseRepository.findCourseByAuthor(username);
    }
}
