package com.example.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobApplication 
{

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private Long userId;

private Long jobId;

private String status;

private String jobTitle;

private String company;

private String location;

public String getJobTitle() {
	return jobTitle;
}

public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Long getJobId() {
	return jobId;
}

public void setJobId(Long jobId) {
	this.jobId = jobId;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}



}
