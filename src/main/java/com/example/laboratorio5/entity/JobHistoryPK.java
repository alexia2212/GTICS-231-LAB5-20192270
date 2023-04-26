package com.example.laboratorio5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class JobHistoryPK implements Serializable {
    @Column(name = "job_history_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobHistoryId;
    @Column(name = "employee_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Column(name = "start_date")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Timestamp startDate;

    public int getJobHistoryId() {
        return jobHistoryId;
    }

    public void setJobHistoryId(int jobHistoryId) {
        this.jobHistoryId = jobHistoryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryPK that = (JobHistoryPK) o;
        return jobHistoryId == that.jobHistoryId && employeeId == that.employeeId && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobHistoryId, employeeId, startDate);
    }
}
