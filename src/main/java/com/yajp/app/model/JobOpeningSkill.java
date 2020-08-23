package com.yajp.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "job_openings_skills", uniqueConstraints = {
        @UniqueConstraint(columnNames = "jobSkillId")
})
public class JobOpeningSkill {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobSkillId;
	
	@ManyToOne
	@JoinColumn(name="job_opening_id", nullable=false)
	private JobOpening jobOpening;
	
	private String skillDescription;
	private Boolean isMandatory;
	public Long getJobSkillId() {
		return jobSkillId;
	}
	public void setJobSkillId(Long jobSkillId) {
		this.jobSkillId = jobSkillId;
	}
	
	@JsonBackReference("skills")
	public JobOpening getJobOpening() {
		return jobOpening;
	}
	public void setJobOpening(JobOpening jobOpening) {
		this.jobOpening = jobOpening;
	}
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
	public Boolean getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	
}
