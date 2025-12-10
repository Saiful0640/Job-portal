package com.jobms.job.mapper;

import com.jobms.job.dto.JobDto;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;
import com.jobms.job.model.Job;

import java.util.List;

public class JobMapper {
    public static JobDto mapToJobWithCompanyDto(
            Job job, Company company, List<Review> reviews){
        JobDto jobDTO = new JobDto();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalaray(job.getMaxSalary());
        jobDTO.setMinSalaray(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
