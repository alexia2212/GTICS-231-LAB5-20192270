package com.example.laboratorio5.repository;

import com.example.laboratorio5.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {
}
