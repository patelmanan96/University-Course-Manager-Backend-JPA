package com.example.myappJPA.repositories;

import com.example.myappJPA.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("SELECT topics FROM Topic topics WHERE topics.lesson.id =: lid")
    List<Topic> findByLessonId(@Param("lid") int lid);
}
