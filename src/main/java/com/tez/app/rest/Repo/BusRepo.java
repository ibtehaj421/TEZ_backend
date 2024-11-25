package com.tez.app.rest.Repo;
import com.tez.app.rest.DTO.BusDTO;
import com.tez.app.rest.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BusRepo extends JpaRepository<Bus,Integer> {

    boolean existsByLicPlateNumber(String licPlateNumber);

    @Query(value = "SELECT * FROM bus WHERE orgid = :id",nativeQuery = true)
    List<Bus> findByID(@Param("id") long id);

    @Query(value = "SELECT * FROM bus WHERE driverid = :input",nativeQuery = true)
    Bus findByDriverID(@Param("input") long input);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bus set driverid = :id where id = :self",nativeQuery = true)
    int updateDriver(@Param("id") long id, @Param("self") long self);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bus SET route_id = :newroute WHERE id = :self",nativeQuery = true)
    int updateRoute(@Param("newroute") long newroute, @Param("self") long self);

    @Query(value = "insert into bus (model_name,capacity,driverid,lic_plate_num,orgid,route_id) values (:model,:cap,:driverid,:lic,:org,:route)",nativeQuery = true)
    int insertBus(@Param("model") String model,@Param("cap") int cap,@Param("driverid") Long driverid,@Param("org") long org,@Param("route") Long route);

    @Query(value = "select * from bus where route_id = :rt",nativeQuery = true)
    List<Bus> findByRoute(@Param("rt") long rt);
}
