package com.yajp.app.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yajp.app.controllers.payload.ApplyJobResponse;
import com.yajp.app.controllers.payload.UserJobApplicationCollection;
import com.yajp.app.controllers.payload.UserJobApplicationPayload;
import com.yajp.app.model.UserJobApplication;
import com.yajp.app.service.JobApplicationService;
import com.yajp.security.CurrentUser;
import com.yajp.security.UserPrincipal;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationController.class);
	
	@Autowired
	JobApplicationService applicationService;
	
	@PostMapping("/apply/{jobOpeningId}")
    @PreAuthorize("hasRole('USER')")
	public ApplyJobResponse apply(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long jobOpeningId) {
		LOGGER.debug("in /application/apply {}  {}", userPrincipal, jobOpeningId);
		ApplyJobResponse response = new ApplyJobResponse(); 
		try {
			UserJobApplication jobApp = applicationService.getUserJobApplication(userPrincipal.getId(), jobOpeningId);
			if(jobApp != null) {
				response.setRequestStatus("error");
				response.setErrorMessage("Already Applied!");
			}else {
				boolean isApplied = applicationService.applyForJobOpening(userPrincipal.getId(), jobOpeningId);
				if(isApplied) {
					jobApp = applicationService.getUserJobApplication(userPrincipal.getId(), jobOpeningId);
					response.setUserJobApplication(jobApp);
					response.setRequestStatus("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setRequestStatus("error");
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	@GetMapping("/getall")
    @PreAuthorize("hasRole('USER')")
	public UserJobApplicationCollection getAllApplications(@CurrentUser UserPrincipal userPrincipal) {
		UserJobApplicationCollection allApplications = new UserJobApplicationCollection();
		LOGGER.debug("in application/getall {}  {}", userPrincipal);
		List<UserJobApplication> allApps = applicationService.getAllApplications(userPrincipal.getId());
		
		List<UserJobApplicationPayload> allPayloadApps = convertToAppPayload(allApps);
		allApplications.setApplications(allPayloadApps);
		return allApplications;
	}
	
	@GetMapping("/get/{jobOpeningId}")
    @PreAuthorize("hasRole('USER')")
	public UserJobApplicationCollection getApplication(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long jobOpeningId) {
		UserJobApplicationCollection allApplications = new UserJobApplicationCollection();
		LOGGER.debug("in application/get {}  {}", userPrincipal, jobOpeningId);
		try {
			List<UserJobApplicationPayload> allPayloadApps = convertToAppPayload(Collections.singletonList(
					applicationService.getUserJobApplication(userPrincipal.getId(), jobOpeningId)
			));
			allApplications.setApplications(allPayloadApps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allApplications;
	}
	
	public List<UserJobApplicationPayload> convertToAppPayload(List<UserJobApplication> allApps){
		List<UserJobApplicationPayload> allPayloadApps = allApps.stream().map(app -> {
			UserJobApplicationPayload payloadApp = new UserJobApplicationPayload();
			payloadApp.setJobApplicationId(app.getJobApplicationId());
			payloadApp.setStatus(app.getStatus());
			if(app.getJobOpening() != null) {
				payloadApp.setTitle(app.getJobOpening().getTitle());
				payloadApp.setJobDescription(app.getJobOpening().getJobDescription());
			}
			return payloadApp;
		}).collect(Collectors.toList());
		return allPayloadApps;
	}
}
