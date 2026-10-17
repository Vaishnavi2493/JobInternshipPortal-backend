package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.demo.user.Job;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController 
{
    
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@PostMapping
	public Job createJob(@Valid @RequestBody Job job)
	{
		return jobRepository.save(job);
	}
	
	@GetMapping
	public List<Job> getAllJobs()
	{
		return jobRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Job> searchJob(@RequestParam String title)
	{
		return jobRepository.findByTitleContainingIgnoreCase(title);
	}
	
	@GetMapping("/search/location")
	public List<Job> searchJobsByLocation(@RequestParam String location)
	{
		return jobRepository.findByLocationContainingIgnoreCase(location);
	}
	
	@PutMapping("/{id}")
	   public Job updateJob(@PathVariable Long id,@RequestBody Job job)
	   {
		   Job existingJob=jobRepository.findById(id)
				   .orElseThrow(() -> new RuntimeException("Job Not Found"));
		   
		   existingJob.setTitle(job.getTitle());
		   existingJob.setDescription(job.getDescription());
		   existingJob.setLocation(job.getLocation());
		   existingJob.setCompany(job.getCompany());
		   existingJob.setSalary(job.getSalary());
		   
		   return jobRepository.save(existingJob);
		   
	   }
	
	@DeleteMapping("/{id}")
	public String deleteJob(@PathVariable Long id)
	{
		if(jobApplicationRepository.existsByJobId(id))
		{
		   return "Job Cannot be deleted because applications exist";	
		}
		
		jobRepository.deleteById(id);
		
		return "Job deleted Successfully";
	}
	
	

}
