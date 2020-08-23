package com.yajp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yajp.app.model.JobOpening;
import com.yajp.app.repository.JobOpeningsRepository;

@Service
@Transactional
public class JobOpeningsServiceImpl implements JobOpeningsService {

	@Autowired
	JobOpeningsRepository joRepository;
	
	@Override
	public List<JobOpening> getAllOpenings() {
		return joRepository.findAll();
	}

	@Override
	public Optional<JobOpening> getJobOpening(Long jobOpeningId) {
		return joRepository.findById(jobOpeningId);
	}

	@Override
	public List<JobOpening> getActiveJobOpening() {
		return joRepository.getActiveJobOpening();
	}

}
