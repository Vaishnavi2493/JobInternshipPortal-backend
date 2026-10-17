package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> 
{
	
	List<JobApplication> findByUserId(Long userId);
	
	List<JobApplication> findByJobId(Long jobId);
	
	boolean existsByJobId(Long jobId);
	
	boolean existsByUserIdAndJobId(Long userId,Long jobId);

}

