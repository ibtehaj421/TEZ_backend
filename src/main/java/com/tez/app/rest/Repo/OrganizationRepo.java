package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization, Integer> {
}
