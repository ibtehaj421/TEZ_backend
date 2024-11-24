package com.tez.app.rest.Repo;

import com.tez.app.rest.Model.User;
import com.tez.app.rest.Model.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo  extends JpaRepository<UserBase, Long>{
    UserBase findByname(String name);

    UserBase findByemail(String email);
    boolean existsByemail(String email);
    UserBase findByid(Long id);
}
