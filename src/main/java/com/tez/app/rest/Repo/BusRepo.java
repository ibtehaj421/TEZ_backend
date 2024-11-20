package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusRepo extends JpaRepository<Bus,Integer> {
}
