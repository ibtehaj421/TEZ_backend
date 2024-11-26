package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

    Organization findByName(String name);
    boolean existsByName(String name);
    @Query(value = "Select role FROM organization WHERE name = :name", nativeQuery = true)
    String getRole(@Param("name") String name);

    @Query(value = "SELECT name FROM organization WHERE id = :orgID",nativeQuery = true)
    String fetchName(@Param("orgID") long orgID);

    @Query(value = "SELECT id FROM organization WHERE name = :name",nativeQuery = true)
    long fetchId(@Param("name") String name);
}
