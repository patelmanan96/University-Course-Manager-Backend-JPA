package com.example.myappJPA;

import com.example.myappJPA.models.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {}