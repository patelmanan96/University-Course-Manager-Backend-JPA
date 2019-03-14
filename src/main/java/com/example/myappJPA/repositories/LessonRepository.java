package com.example.myappJPA.repositories;

import com.example.myappJPA.models.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    @Query("SELECT lessons FROM Lesson lessons WHERE lessons.module.id =:mid")
    List<Lesson> findByModuleId(@Param("mid") int mid);
}
