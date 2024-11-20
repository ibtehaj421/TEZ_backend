package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.BusTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTicketRepo extends JpaRepository<BusTicket, Integer> {
}
