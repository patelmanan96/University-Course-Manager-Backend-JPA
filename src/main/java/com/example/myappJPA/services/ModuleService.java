package com.example.myappJPA.services;

import com.example.myappJPA.models.Course;
import com.example.myappJPA.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class ModuleService {
    @Autowired
    private CourseService courseService;

    @PostMapping("/api/courses/{cid}/modules")
    public void createModule(@PathVariable("cid") int courseId, @RequestBody Module module) {
        courseService.findCourseById(courseId).getModules().add(module);
    }

    @GetMapping("/api/courses/{cid}/modules")
    public ArrayList<Module> findAllModules(@PathVariable("cid") int courseId) {
        System.out.println("MOD FIND HIT + "  + courseService.findCourseById(courseId).getModules());
        return courseService.findCourseById(courseId).getModules();
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(@PathVariable("mid") int mid) {
        for (Course c : courseService.findAllCourses()) {
            for (Module m : c.getModules()) {
                if (m.getId() == mid) {
                    return m;
                }
            }
        }
        return null;
    }

    @PutMapping("/api/modules/{mid}")
    public void updateModule(@PathVariable("mid") int mid, @RequestBody Module module) {
        System.out.println(module.getTitle());
        for (Course c : courseService.findAllCourses()) {
            for (Module m : c.getModules()) {
                if (m.getId() == mid) {
                    m.setTitle(module.getTitle());
                    m.setLessons(module.getLessons());
                }
            }
        }
    }

    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(@PathVariable("mid") int mid) {
        for (Course i : courseService.findAllCourses()) {
            Iterator itr = i.getModules().iterator();
            while (itr.hasNext()) {
                Module x = (Module) itr.next();
                if (x.getId() == mid) {
                    itr.remove();
                }
            }
        }
    }
}
