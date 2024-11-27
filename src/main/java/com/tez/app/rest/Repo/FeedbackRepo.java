package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    @Query(value = "SELECT * FROM feedback WHERE orgid = :fetch",nativeQuery = true)
    List<Feedback> findByOrgID(@Param("fetch") long fetch);
}
