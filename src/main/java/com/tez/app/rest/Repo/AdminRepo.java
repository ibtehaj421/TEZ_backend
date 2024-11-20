package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepo extends JpaRepository<Admin, Long> {

}
