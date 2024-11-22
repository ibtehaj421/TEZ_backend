package com.tez.app.rest.Repo;


import com.tez.app.rest.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Long> {

   List<Seat> findByBusID(long busID);
}
