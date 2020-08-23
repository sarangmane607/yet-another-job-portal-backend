package com.yajp.app.controllers.payload;

import com.yajp.app.model.UserJobApplication;

public class ApplyJobResponse {

	UserJobApplication userJobApplication;
	String requestStatus;
	String errorMessage;
	
	public UserJobApplication getUserJobApplication() {
		return userJobApplication;
	}
	public void setUserJobApplication(UserJobApplication userJobApplication) {
		this.userJobApplication = userJobApplication;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
