package com.yajp.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "job_openings", uniqueConstraints = {
        @UniqueConstraint(columnNames = "jobOpeningId")
})
public class JobOpening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOpeningId;
	@Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String jobDescription;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
    
    @OneToMany(mappedBy="jobOpening", fetch=FetchType.EAGER)
    private Set<JobOpeningSkill> skills;
    

    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    Set<UserJobApplication> jobApplications;
    
    private Integer minExperienceInYears;
    private Integer maxExperienceInYears;
    private Integer minOfferingCTC;
    private Integer maxOfferingCTC;
    private String location;
    private Date validFrom;
    private Date validTill;
	private Integer totalVacancies;
	private Integer availableVacancies;
    private Date createdOn;
    
	public Long getJobOpeningId() {
		return jobOpeningId;
	}
	public void setJobOpeningId(Long jobOpeningId) {
		this.jobOpeningId = jobOpeningId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public EmploymentType getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}
    
    public Set<UserJobApplication> getJobApplications() {
		return jobApplications;
	}
    @JsonManagedReference("job-applications")
	public void setJobApplications(Set<UserJobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}
	
	@JsonManagedReference("skills")
	public Set<JobOpeningSkill> getSkills() {
		return skills;
	}
	public void setSkills(Set<JobOpeningSkill> skills) {
		this.skills = skills;
	}
	public Integer getMinExperienceInYears() {
		return minExperienceInYears;
	}
	public void setMinExperienceInYears(Integer minExperienceInYears) {
		this.minExperienceInYears = minExperienceInYears;
	}
	public Integer getMaxExperienceInYears() {
		return maxExperienceInYears;
	}
	public void setMaxExperienceInYears(Integer maxExperienceInYears) {
		this.maxExperienceInYears = maxExperienceInYears;
	}
	public Integer getMinOfferingCTC() {
		return minOfferingCTC;
	}
	public void setMinOfferingCTC(Integer minOfferingCTC) {
		this.minOfferingCTC = minOfferingCTC;
	}
	public Integer getMaxOfferingCTC() {
		return maxOfferingCTC;
	}
	public void setMaxOfferingCTC(Integer maxOfferingCTC) {
		this.maxOfferingCTC = maxOfferingCTC;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTill() {
		return validTill;
	}
	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}
	public Integer getTotalVacancies() {
		return totalVacancies;
	}
	public void setTotalVacancies(Integer totalVacancies) {
		this.totalVacancies = totalVacancies;
	}
	public Integer getAvailableVacancies() {
		return availableVacancies;
	}
	public void setAvailableVacancies(Integer availableVacancies) {
		this.availableVacancies = availableVacancies;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
    
}
