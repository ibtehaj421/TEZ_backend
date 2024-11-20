package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
