package com.example.laboratorio5.repository;

import com.example.laboratorio5.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Integer> {
}
