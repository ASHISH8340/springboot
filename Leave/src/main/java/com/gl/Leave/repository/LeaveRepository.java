package com.gl.Leave.repository;

import com.gl.Leave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
//    @Query(value = "SELECT * FROM Leave u WHERE u.employeeid = ?1", nativeQuery = true)
    Optional<Leave> findByEmployeeid(Long employeeid);


}
