package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.BusPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BusPassRepo extends JpaRepository<BusPass, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update bus_pass set status = 'paid' where user_id = :id",nativeQuery = true)
    void changeToPaid(@Param("id") long id);

    BusPass getByUserId(long userId);
}
