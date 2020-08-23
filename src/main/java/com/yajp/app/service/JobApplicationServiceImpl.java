package com.yajp.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yajp.app.model.JobApplicationKey;
import com.yajp.app.model.UserJobApplication;
import com.yajp.app.repository.JobApplicationRepository;
import com.yajp.app.repository.JobOpeningsRepository;
import com.yajp.security.model.User;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
    CrudRepository<User, Long> userRepository;
	@Autowired
    JobApplicationRepository jobApplicationRepository;

	@Autowired
	JobOpeningsRepository joRepository;
	
	@Autowired
	private ApplicationContext appContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationServiceImpl.class);
	
	@Override
	public boolean applyForJobOpening(Long userId, Long jobOpeningId) throws SQLException {
		int iUpdatedCount = 0;
		DataSource ds = (DataSource)appContext.getBean("dataSource");
		PreparedStatement pstmt = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			LOGGER.debug("got connection : {}", connection);
			
			pstmt = null;
			String[] paramArray = new String[] {""+jobOpeningId, ""+userId, "Applied"};
			pstmt = connection.prepareStatement("insert into USER_JOB_APPLICATION(JOB_OPENING_ID, USER_ID, STATUS) values(?, ?, ?)");
			for (int i = 0; i < paramArray.length; i++) {
				LOGGER.debug("Setting parameter {} = {}", new Object[] { i + 1, paramArray[i] });
				pstmt.setString(i + 1, paramArray[i]);
			}
			iUpdatedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("error in applyForJobOpening", e);
			throw e;
		}finally {
			pstmt = null;
			if(connection != null) connection.close();
		}
		return iUpdatedCount > 0;
		/*
		Optional<User> userOpt = userRepository.findById(userId);
		Optional<JobOpening> jobOpeningOpt = joRepository.findById(jobOpeningId);
		if(userOpt.isPresent()) {
			if(jobOpeningOpt.isPresent()) {
				JobOpening opening = jobOpeningOpt.get();
				User user = userOpt.get();
				UserJobApplication application = new UserJobApplication();
				application.setJobOpening(opening);
				application.setUser(user);
				application.setStatus("Applied");
				user.getUserApplications().add(application);
				userRepository.save(user);
			}else {
				throw new RuntimeException("Job Opening not found while applying for Job");
			}
		}else {
			throw new RuntimeException("User not found while applying for Job");
		}
		*/
	}

	@Override
	public List<UserJobApplication> getAllApplications(Long userId) {
		return jobApplicationRepository.getAllApplications(userId);
	}

	@Override
	public UserJobApplication getUserJobApplication(Long userId, Long jobOpeningId) throws Exception {
		JobApplicationKey id = new JobApplicationKey();
		id.setUserId(userId);
		id.setJobOpeningId(jobOpeningId);
		Optional<UserJobApplication> jobAppOpt = jobApplicationRepository.findById(id);
		LOGGER.debug("jobAppOpt : {}", jobAppOpt);
		if(jobAppOpt.isPresent()) {
			UserJobApplication jobApp = jobAppOpt.get();
			LOGGER.debug("jobApp : {}", jobApp);
			return jobApp;
		}
		return null;
	}

}
