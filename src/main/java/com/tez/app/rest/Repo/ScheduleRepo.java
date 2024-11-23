package com.tez.app.rest.Repo;


import com.tez.app.rest.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    //add schedule provided orgid

    List<Schedule> findByOrgID(long orgID);
    //check for duplicates
    @Query(value = "SELECT COUNT(s) > 0 FROM schedule s WHERE s.from_and_to = :from_to AND s.orgid = :inputid AND s.time = :inputTime",nativeQuery = true)
    boolean checkForDuplicate(@Param("from_to") String from_and_to,@Param("inputid") long inputid,@Param("inputTime") String inputTime);

    //edit schedule timings and automate the process to also add time of day
    @Modifying
    @Transactional
    @Query(value = "UPDATE schedule SET from_and_to = :from_to WHERE id = :inputid",nativeQuery = true)
    int updateTimings(@Param("from_to") String from_to,@Param("inputid") Integer inputid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE schedule SET time = :inputTime WHERE id = :inputid",nativeQuery = true)
    int updateTimeOfDay(@Param("inputTime") String inputTime,@Param("inputid") Integer inputid);



}
