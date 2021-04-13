package com.example.demo.repository;

import com.example.demo.model.BillService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillServiceRepository extends JpaRepository<BillService, Long> {
}
