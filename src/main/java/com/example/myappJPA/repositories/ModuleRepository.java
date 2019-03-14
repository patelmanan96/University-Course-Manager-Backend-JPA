package com.example.myappJPA.repositories;

import com.example.myappJPA.models.Module;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

    @Query("SELECT modules FROM Module modules WHERE modules.course.courseId =:cid")
    List<Module> findByCourseId(@Param("cid") int cid);
}
