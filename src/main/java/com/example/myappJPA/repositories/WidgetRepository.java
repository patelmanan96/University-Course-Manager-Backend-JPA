package com.example.myappJPA.repositories;

import com.example.myappJPA.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget,Integer> {

    @Query("SELECT widgets FROM Widget widgets WHERE widgets.topic.id =:tid")
    List<Widget> findByTopicId(int tid);

}
