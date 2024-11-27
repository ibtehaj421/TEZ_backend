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
   @Query(value = "UPDATE seats set status = :newStat WHERE busid = :self AND seat_number = :num",nativeQuery = true)
   int changeStatus(@Param("newStat") String status, @Param("self") long self,@Param("num") int num);

   @Query(value = "SELECT status FROM seats WHERE busid = :input AND seat_number = :num",nativeQuery = true)
   String findStatus(@Param("input") long input, @Param("num") int num);

   @Query(value = "SELECT b.orgid FROM bus b JOIN seats s ON b.id = s.busid WHERE s.id = :input",nativeQuery = true)
   long fetchOrgID(@Param("input") long input);

   List<Seat> findAllByBusID(long busID);

   @Query(value = "SELECT * FROM seats WHERE busid = :bus AND status = 'available' ORDER BY seat_number ASC  LIMIT 1",nativeQuery = true)
   Seat findFirstAvailableSeat(@Param("bus") long bus);

   @Query(value = "SELECT seat_number FROM seats WHERE id = :seatID",nativeQuery = true)
   String getSeatNumber(@Param("seatID") long seatID);

}
