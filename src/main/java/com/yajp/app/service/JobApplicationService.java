package com.yajp.app.service;

import java.util.List;

import com.yajp.app.model.UserJobApplication;

public interface JobApplicationService {
	
	public boolean applyForJobOpening(Long userId, Long jobOpeningId) throws Exception;
	public UserJobApplication getUserJobApplication(Long userId, Long jobOpeningId) throws Exception;
	public List<UserJobApplication> getAllApplications(Long userId);
}
