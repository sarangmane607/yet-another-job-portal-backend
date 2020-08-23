package com.yajp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yajp.app.model.UserJobApplication;
import com.yajp.app.model.JobApplicationKey;

public interface JobApplicationRepository extends JpaRepository<UserJobApplication, JobApplicationKey> {
	
	@Query(nativeQuery = true, value="SELECT * FROM USER_JOB_APPLICATION where user_id = :userid")
	List<UserJobApplication> getAllApplications(@Param("userid") Long userid);
}
