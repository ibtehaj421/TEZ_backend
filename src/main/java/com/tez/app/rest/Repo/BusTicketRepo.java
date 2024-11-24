package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.BusTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BusTicketRepo extends JpaRepository<BusTicket, Long> {

        @Modifying
        @Transactional
        @Query(value = "UPDATE bus_ticket SET status = :input WHERE id = :id",nativeQuery = true)
        int updateStatus(@Param("id") Long id, @Param("input") String input);
}
