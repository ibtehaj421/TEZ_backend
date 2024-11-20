package com.tez.app.rest.Repo;

import com.tez.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo  extends JpaRepository<User, Long>{

}
