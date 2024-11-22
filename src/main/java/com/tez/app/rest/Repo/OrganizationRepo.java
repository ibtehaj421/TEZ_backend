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
}
