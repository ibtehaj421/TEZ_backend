package com.tez.app.rest.Repo;

import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Driver;
import com.tez.app.rest.Model.User;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepo  extends JpaRepository<UserBase, Long>{
    UserBase findByname(String name);

    UserBase findByemail(String email);
    boolean existsByemail(String email);
    UserBase findByid(Long id);

    @Query(value = "select id from user_base where email = :email",nativeQuery = true)
    long findIDByemail(@Param("email") String email);

    //find in users
    @Query(value = "select ub.id,ub.name,ub.email,ub.password,u.pass,u.role from user_base ub join users u on ub.id = u.id where ub.id = :id",nativeQuery = true)
    User findUserByid(@Param("id") Long id);
    //find in admins
    @Query(value = "select ub.id,ub.name,ub.email,ub.password,u.adminid,u.orgid from user_base ub join admins u on ub.id = u.id where ub.id = :id",nativeQuery = true)
    Admin findAdminByid(@Param("id") Long id);

    @Query(value = "select o.role from organization o join admins a on o.id = a.orgid where a.id = 13",nativeQuery = true)
    Role findRolebyid(@Param("id") Long id);


    //find in drivers
    @Query(value = "select ub.id,ub.name,ub.email,ub.password,u.level,u.orgid from user_base ub join drivers u on ub.id = u.id where ub.id = :id",nativeQuery = true)
    Driver findDriverByid(@Param("id") Long id);

    @Query(value = "SELECT * FROM user_base ub join admins a on ub.id = a.id where a.orgid = :name  ",nativeQuery = true)
    List<Admin> getAdminsByOrg(@Param("name") long name);
}
