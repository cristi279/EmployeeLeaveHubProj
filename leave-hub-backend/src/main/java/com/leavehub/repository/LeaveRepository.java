package com.leavehub.repository;

import com.leavehub.model.LeaveRequest;
import com.leavehub.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByDepartment(String department);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    @Query("SELECT COUNT(l) FROM LeaveRequest l WHERE l.department = :department " +
            "AND l.status = com.leavehub.model.LeaveStatus.APPROVED " +
            "AND (:excludeId IS NULL OR l.id <> :excludeId) " +
            "AND (l.startDate <= :endDate AND l.endDate >= :startDate)")
    long countOverlappingApprovedLeaves(
            @Param("department") String department,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("excludeId") Long excludeId
    );
}