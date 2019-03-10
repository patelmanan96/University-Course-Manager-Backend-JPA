package com.example.myappJPA.services;

import com.example.myappJPA.models.Course;
import com.example.myappJPA.models.Faculty;
import com.example.myappJPA.models.Lesson;
import com.example.myappJPA.models.Module;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();
    private int addCount=0;

    CourseService() {
        Course sample = new Course();
        sample.setAuthor("Dave");
        sample.setTitle("WebDev");
        sample.setId(1157);
        sample.setModules(new ArrayList<>());

        Module m1 = new Module();
        m1.setLessons(new ArrayList<>());
        m1.setId(12);
        m1.setTitle("Module 1");

        Lesson l1 = new Lesson();
        l1.setTitle("Lesson 1");
        l1.setId(102);

        m1.getLessons().add(l1);

        sample.getModules().add(m1);

        courses.add(sample);
    }

    @PostMapping("/api/courses")
    public void createCourse(@RequestBody Course course){
        System.out.println("COURSE ADD CALLED" + " : "+addCount++);
        boolean loop = false;
        while(true){
            double x = (Math.random() * ((10000) + 1));
            for(Course c: this.courses){
                if(c.getId() == x){
                    loop = true;
                }
            }
            if(!loop){
                course.setId((int)x);
                break;
            }
        }

        System.out.println(course.getAuthor());
        System.out.println(course.getTitle());
        courses.add(course);
        System.out.println("ARRAY SIZE POST ADDING : "+courses.size());
    }

    @GetMapping("/api/courses")
    public ArrayList<Course> findAllCourses(){
        System.out.println("GET COURSES HIT");
        return courses;
    }

    @GetMapping("/api/courses/{cid}")
    public Course findCourseById(@PathVariable("cid") int cid){
        System.out.println("QUERIED : " + cid);
        for(Course c : courses){
            if(c.getId() == cid){
                return c;
            }
        }
        return new Course();
    }

    @PutMapping("/api/courses/{cid}")
    public void updateCourse(@PathVariable("cid") int cid, @RequestBody Course course){
        if(courses.removeIf(c -> c.getId() == cid)){
            courses.add(course);
        }
    }

    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(@PathVariable("cid") int cid){
        System.out.println("DELETE + "+cid);
        courses.removeIf(c -> c.getId() == cid);
    }

    @GetMapping("/api/sessionCourses")
    public List<Course> findCoursesForSessionUser(HttpSession session){
        System.out.println("Find Course Session hit : "+session.getAttribute("currentUser").toString());
        String sessionUname = ((Faculty)session.getAttribute("currentUser")).getUsername();
        List<Course> ret = new ArrayList<>();
        for(Course c:courses){
            System.out.println("Course Author : "+c.getAuthor());
            if(sessionUname.equals(c.getAuthor())){
                ret.add(c);
            }
        }
        System.out.println("SIZE : "+ret.size());
        return ret;
    }

}
