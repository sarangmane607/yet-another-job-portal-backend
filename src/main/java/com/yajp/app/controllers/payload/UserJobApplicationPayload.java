package com.yajp.app.controllers.payload;

import com.yajp.app.model.JobApplicationKey;

public class UserJobApplicationPayload {
	
	private JobApplicationKey jobApplicationId;
	private String status;
	private String title;
    private String jobDescription;

	public JobApplicationKey getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(JobApplicationKey jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
