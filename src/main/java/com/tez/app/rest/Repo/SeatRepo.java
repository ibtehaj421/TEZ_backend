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
}
