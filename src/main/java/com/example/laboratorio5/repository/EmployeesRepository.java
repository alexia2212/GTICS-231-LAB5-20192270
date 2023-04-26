package com.example.laboratorio5.repository;

import com.example.laboratorio5.entity.Employees;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE employees SET manager_id = null " +
            "WHERE employee_id IN " +
            "      (SELECT * FROM " +
            "          (SELECT employee_id FROM employees WHERE manager_id = ?1) AS tmp);", nativeQuery = true)
    void eliminarmanager(int id);

    @Transactional
    @Modifying
    @Query(value = "update departments set manager_id=null where manager_id=?1", nativeQuery = true)
    void actualizardepartamento(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employees set enabled = 0 where employee_id=?1", nativeQuery = true)
    void eliminarempleado(int id);

    @Query(value = "select * from employees where enabled = 1", nativeQuery = true)
    List<Employees> listaenableempleados();



    @Query(value = "SELECT e.* from employees  e inner join jobs on e.job_id = jobs.job_id " +
            "inner join departments d on e.department_id = d.department_id " +
            "inner join locations l on d.location_id = l.location_id " +
            "where (lower(e.first_name) like %?1% or lower(e.last_name) like %?1% or lower(jobs.job_title) like %?1% or lower(l.city) like %?1%)"
            , nativeQuery = true)
    List<Employees> listafiltrada(String filtro);



}
