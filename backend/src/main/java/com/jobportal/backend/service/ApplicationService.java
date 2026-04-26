package com.jobportal.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jobportal.backend.dto.ApplicationResponse;
import com.jobportal.backend.entity.Application;
import com.jobportal.backend.entity.Job;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.exception.ResourceNotFoundException;
import com.jobportal.backend.repository.ApplicationRepository;
import com.jobportal.backend.repository.JobRepository;
import com.jobportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final Cloudinary cloudinary;

    public ApplicationResponse apply(Long jobId, MultipartFile resume,
                                     String email) throws IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
        if (applicationRepository.existsByUserAndJob(user, job)) {
            throw new RuntimeException("Already applied to this job");
        }
        String resumeUrl = null;
        if (resume != null && !resume.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(
                    resume.getBytes(),
                    ObjectUtils.asMap("resource_type", "raw"));
            resumeUrl = (String) uploadResult.get("secure_url");
        }
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setResumeLink(resumeUrl);
        return toResponse(applicationRepository.save(application));
    }

    public List<ApplicationResponse> getMyApplications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        return applicationRepository.findByUser(user)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<ApplicationResponse> getApplicationsForJob(
            Long jobId, String email) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
        return applicationRepository.findByJob(job)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ApplicationResponse updateStatus(Long id, String status,
                                            String email) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found"));
        application.setStatus(
                Application.ApplicationStatus.valueOf(status));
        return toResponse(applicationRepository.save(application));
    }

    private ApplicationResponse toResponse(Application app) {
        ApplicationResponse res = new ApplicationResponse();
        res.setId(app.getId());
        res.setJobId(app.getJob().getId());
        res.setJobTitle(app.getJob().getTitle());
        res.setApplicantName(app.getUser().getName());
        res.setApplicantEmail(app.getUser().getEmail());
        res.setResumeLink(app.getResumeLink());
        res.setStatus(app.getStatus().name());
        res.setAppliedAt(app.getAppliedAt());
        return res;
    }
}