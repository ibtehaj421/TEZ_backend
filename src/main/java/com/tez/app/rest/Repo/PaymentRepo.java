package com.tez.app.rest.Repo;
import com.tez.app.rest.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
