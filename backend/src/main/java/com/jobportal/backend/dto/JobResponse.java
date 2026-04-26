package com.jobportal.backend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private BigDecimal salary;
    private String skillsRequired;
    private String postedByName;
    private Long postedById;
    private LocalDateTime createdAt;
}