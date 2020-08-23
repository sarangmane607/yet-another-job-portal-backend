package com.yajp.app.controllers.payload;

import java.util.List;

import com.yajp.app.model.JobOpening;

public class JobOpeningCollection {
	List<JobOpening> jobs;

	public List<JobOpening> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobOpening> jobs) {
		this.jobs = jobs;
	}
	
}
