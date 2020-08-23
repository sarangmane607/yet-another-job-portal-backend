package com.yajp.app.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yajp.security.model.User;

@Entity
@Table(name = "user_job_application")
public class UserJobApplication {
	
	@EmbeddedId
	private JobApplicationKey jobApplicationId;
	
	@ManyToOne
    @MapsId("job_opening_id")
    @JoinColumn(name = "job_opening_id")
	JobOpening jobOpening;
	
	@ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
	User user;
	
	String status;

	public JobApplicationKey getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(JobApplicationKey jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	
	@JsonBackReference("job-applications")
	public JobOpening getJobOpening() {
		return jobOpening;
	}

	public void setJobOpening(JobOpening jobOpening) {
		this.jobOpening = jobOpening;
	}
	
	@JsonBackReference("user-appl")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
