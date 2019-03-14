package com.example.myappJPA.repositories;

import com.example.myappJPA.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.username=:username")
    User findByUsername(@Param("username") String username);

}
