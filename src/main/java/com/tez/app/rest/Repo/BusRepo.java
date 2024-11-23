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
}
