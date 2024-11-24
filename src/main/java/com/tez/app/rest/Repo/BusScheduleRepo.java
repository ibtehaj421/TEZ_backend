package com.tez.app.rest.Repo;


import com.tez.app.rest.Model.BusSchedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BusScheduleRepo extends CrudRepository<BusSchedule, Integer> {

    //check for duplicates
    @Query(value = "SELECT COUNT(u) > 0 FROM bus_schedule u WHERE u.busid = :bus AND u.scheduleid = :schedule",nativeQuery = true)
    boolean checkForDuplicates(@Param("bus") long bus, @Param("schedule") long schedule);
    //add a value
    @Query(value = "INSERT INTO bus_schedule (busid,scheduleid) VALUES (:bus,:schedule)",nativeQuery = true)
    int insert(@Param("bus") long bus, @Param("schedule") long schedule);

    //remove a value
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bus_schedule WHERE busid = :bus AND scheduleid = :schedule",nativeQuery = true)
    void deletebyID(@Param("bus") long bus, @Param("schedule") long schedule);
}
