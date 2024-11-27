package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route, Integer> {
    //Route findByRouteName(String name);

    @Query(value = "SELECT * FROM routes WHERE id = :id",nativeQuery = true)
    Route findByID(@Param("id") Long id);

    @Query(value = "WITH orgExtractor AS (SELECT id AS org_id FROM organization WHERE name = :org), " +
            "routeExtractor AS (SELECT route_id FROM bus WHERE orgid = (SELECT org_id FROM orgExtractor)) " +
            "SELECT r.* FROM routes r INNER JOIN routeExtractor re ON r.id = re.route_id",nativeQuery = true)
    List<Route> findByOrg(@Param("org") String org);


    @Query(value = "with routevalue as (select route_id as route from bus where driverid = :driver) select * from routes where id = (select route from routevalue)",nativeQuery = true)
    Route findByDriver(@Param("driver") long driver);
}
