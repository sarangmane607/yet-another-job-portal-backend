package com.yajp.app.service;

import java.util.List;
import java.util.Optional;

import com.yajp.app.model.JobOpening;

public interface JobOpeningsService {
	
	public List<JobOpening> getAllOpenings();
	public Optional<JobOpening> getJobOpening(Long jobOpeningId);
	public List<JobOpening> getActiveJobOpening();
}
