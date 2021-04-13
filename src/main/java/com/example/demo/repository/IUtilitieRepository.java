package com.example.demo.repository;

import com.example.demo.model.Utilitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilitieRepository extends JpaRepository<Utilitie, Long> {
}
