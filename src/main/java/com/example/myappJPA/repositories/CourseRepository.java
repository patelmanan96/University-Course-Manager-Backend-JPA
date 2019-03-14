package com.example.myappJPA.repositories;

import com.example.myappJPA.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query("SELECT course FROM Course course WHERE course.author=:courseAuth")
    List<Course> findCourseByAuthor(@Param("courseAuth") String courseAuth);
}
