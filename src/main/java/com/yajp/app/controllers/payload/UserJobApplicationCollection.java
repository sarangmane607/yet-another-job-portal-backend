package com.yajp.app.controllers.payload;

import java.util.List;

public class UserJobApplicationCollection {
	List<UserJobApplicationPayload> applications;

	public List<UserJobApplicationPayload> getApplications() {
		return applications;
	}

	public void setApplications(List<UserJobApplicationPayload> applications) {
		this.applications = applications;
	}
	
}
