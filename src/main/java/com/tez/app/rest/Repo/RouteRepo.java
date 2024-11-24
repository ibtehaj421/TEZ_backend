package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepo extends JpaRepository<Route, Integer> {
    //Route findByRouteName(String name);

    @Query(value = "SELECT * FROM routes WHERE id = :id",nativeQuery = true)
    Route findByID(@Param("id") Long id);
}
