package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.user.Job;
import com.example.demo.user.JobApplication;
import com.example.demo.user.User;

@RestController
@RequestMapping("/applications")
public class JobApplicationController 
{
   @Autowired
	private JobApplicationRepository applicationRepository;
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private JobRepository jobRepository;
   
   @PostMapping
   public ResponseEntity<?> applyForJob(@RequestBody JobApplication application,
		                                Authentication authentication)
   {
	   String email=authentication.getName();
	   
	   User user=userRepository.findByEmail(email)
			   .orElseThrow(()->new RuntimeException("User not found"));
	   
	   application.setUserId(user.getId());
	   
	   if(applicationRepository.existsByUserIdAndJobId(user.getId(),application.getJobId()))
	   {
		   return ResponseEntity.badRequest()
				   .body(java.util.Map.of("message","You have already applied for this job"));
	   }
	   
	   application.setStatus("APPLIED");
	   
	   Job job=jobRepository.findById(application.getJobId())
			   .orElseThrow(()->new RuntimeException("Job bot found"));
	   
	   application.setJobTitle(job.getTitle());
	   application.setCompany(job.getCompany());
	   application.setLocation(job.getLocation());
	   
	   return ResponseEntity.ok(applicationRepository.save(application));
   }
   
   @GetMapping("/user")
   public List<JobApplication> getMyApplications(Authentication authentication)
   {
	 String email=authentication.getName();
	 
	 User user=userRepository.findByEmail(email)
			 .orElseThrow(()->new RuntimeException("User not found"));
	 
	 return applicationRepository.findByUserId(user.getId());
   }
   
   @GetMapping
   public List<JobApplication> getAllApplications()
   {
	  return applicationRepository.findAll(); 
   }
   
   
   @GetMapping("/job/{jobId}")
   public List<JobApplication> getApplicationByJob(@PathVariable Long jobId)
   {
	return applicationRepository.findByJobId(jobId);  
   }
   
   @PutMapping("/{id}/status")
   public JobApplication updateStatus(
		   @PathVariable Long id,
		   @RequestParam String status)
   {
	   JobApplication application = applicationRepository.findById(id)
			    .orElseThrow(() -> new RuntimeException("Application not found"));
	   application.setStatus(status);
	   
	   return applicationRepository.save(application);
			   
   }
   
   

}
