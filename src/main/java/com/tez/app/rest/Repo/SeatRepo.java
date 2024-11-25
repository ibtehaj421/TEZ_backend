package com.tez.app.rest.Repo;


import com.tez.app.rest.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Long> {

   List<Seat> findByBusID(long busID);

   @Modifying
   @Transactional
   @Query(value = "UPDATE seats set status = :newStat WHERE id = :self",nativeQuery = true)
   int changeStatus(@Param("newStat") String status, @Param("self") long self);

   @Query(value = "SELECT status FROM seats WHERE id = :input",nativeQuery = true)
   String findStatus(@Param("input") long input);

   @Query(value = "SELECT b.orgid FROM bus b JOIN seats s ON b.id = s.busid WHERE s.id = :input",nativeQuery = true)
   long fetchOrgID(@Param("input") long input);

   List<Seat> findAllByBusID(long busID);
}
