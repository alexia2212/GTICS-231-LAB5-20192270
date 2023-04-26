package com.example.laboratorio5.repository;

import com.example.laboratorio5.dtos.SalariosDTO;
import com.example.laboratorio5.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Jobs, String> {

    @Query(value = "SELECT p.job_title, MAX(e.salary) AS maxsalary, MIN(e.salary) AS minsalary, SUM(e.salary) AS totalsalary, ROUND(AVG(e.salary), 2) AS promediosalary " +
            "FROM employees e " +
            "JOIN jobs p ON e.job_id = p.job_id " +
            "GROUP BY p.job_title;", nativeQuery = true)
    List<SalariosDTO> obtenerSalariosPorPuesto();

}
