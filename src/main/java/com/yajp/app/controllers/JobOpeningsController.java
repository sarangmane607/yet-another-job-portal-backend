package com.yajp.app.controllers;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yajp.app.controllers.payload.JobOpeningCollection;
import com.yajp.app.model.JobOpening;
import com.yajp.app.service.JobOpeningsService;

@RestController
@RequestMapping("/jobopenings")
public class JobOpeningsController {

	@Autowired
	private JobOpeningsService JobOpeningsService;

	@GetMapping("/getall")
	public JobOpeningCollection getAllOpenings() {
		JobOpeningCollection joc = new JobOpeningCollection();
		joc.setJobs(JobOpeningsService.getAllOpenings());
		return joc;
	}

	@GetMapping("/get/{id}")
	public JobOpeningCollection getOpening(@PathVariable Long id) {
		Optional<JobOpening> optional = JobOpeningsService.getJobOpening(id);
		JobOpeningCollection joc = new JobOpeningCollection();
		if (optional.isPresent()) {
			joc.setJobs(Collections.singletonList(optional.get()));
		}
		return joc;
	}
	
	@GetMapping("/getallactive")
	public JobOpeningCollection getActiveJobOpening() {
		JobOpeningCollection joc = new JobOpeningCollection();
		joc.setJobs(JobOpeningsService.getActiveJobOpening());
		return joc;
	}
}
