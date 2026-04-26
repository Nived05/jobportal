package com.jobportal.backend.service;

import com.jobportal.backend.dto.JobRequest;
import com.jobportal.backend.dto.JobResponse;
import com.jobportal.backend.entity.Job;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.exception.ResourceNotFoundException;
import com.jobportal.backend.repository.JobRepository;
import com.jobportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Page<JobResponse> getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());
        return jobRepository.findAll(pageable).map(this::toResponse);
    }

    public Page<JobResponse> searchJobs(String keyword,
                                        String location, BigDecimal minSalary, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());
        return jobRepository.searchJobs(keyword, location,
                minSalary, pageable).map(this::toResponse);
    }

    public JobResponse getJobById(Long id) {
        return toResponse(jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found")));
    }

    public JobResponse createJob(JobRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setPostedBy(user);
        return toResponse(jobRepository.save(job));
    }

    public JobResponse updateJob(Long id, JobRequest request,
                                 String email) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
        if (!job.getPostedBy().getEmail().equals(email)) {
            throw new AccessDeniedException("Not your job");
        }
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setSkillsRequired(request.getSkillsRequired());
        return toResponse(jobRepository.save(job));
    }

    public void deleteJob(Long id, String email) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
        if (!job.getPostedBy().getEmail().equals(email)) {
            throw new AccessDeniedException("Not your job");
        }
        jobRepository.delete(job);
    }

    public Page<JobResponse> getMyJobs(String email,
                                       int page, int size) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());
        return jobRepository.findByPostedBy(user, pageable)
                .map(this::toResponse);
    }

    private JobResponse toResponse(Job job) {
        JobResponse res = new JobResponse();
        res.setId(job.getId());
        res.setTitle(job.getTitle());
        res.setDescription(job.getDescription());
        res.setLocation(job.getLocation());
        res.setSalary(job.getSalary());
        res.setSkillsRequired(job.getSkillsRequired());
        res.setPostedByName(job.getPostedBy().getName());
        res.setPostedById(job.getPostedBy().getId());
        res.setCreatedAt(job.getCreatedAt());
        return res;
    }
}