package com.example.laboratorio5.repository;

import com.example.laboratorio5.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Jobs, Integer> {
}
