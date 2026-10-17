package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.Job;

public interface JobRepository extends JpaRepository<Job, Long>
{
	List<Job> findByTitleContainingIgnoreCase(String title);
	
	List<Job> findByLocationContainingIgnoreCase(String location);

}
