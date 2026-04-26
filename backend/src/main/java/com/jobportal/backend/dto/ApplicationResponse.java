package com.jobportal.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationResponse {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String applicantName;
    private String applicantEmail;
    private String resumeLink;
    private String status;
    private LocalDateTime appliedAt;
}