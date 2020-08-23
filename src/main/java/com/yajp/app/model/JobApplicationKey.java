package com.yajp.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobApplicationKey implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4275069171565500894L;

	@Column(name = "job_opening_id")
    Long jobOpeningId;
 
    @Column(name = "user_id")
    Long userId;

	public Long getJobOpeningId() {
		return jobOpeningId;
	}

	public void setJobOpeningId(Long jobOpeningId) {
		this.jobOpeningId = jobOpeningId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}