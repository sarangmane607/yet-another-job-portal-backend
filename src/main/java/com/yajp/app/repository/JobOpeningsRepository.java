package com.yajp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yajp.app.model.JobOpening;

public interface JobOpeningsRepository extends JpaRepository<JobOpening, Long> {
	
	@Query(nativeQuery = true, value="select * from job_openings q where NVL(valid_from, sysdate) <= sysdate AND NVL(valid_till, sysdate) >= sysdate")
	public List<JobOpening> getActiveJobOpening();
}
