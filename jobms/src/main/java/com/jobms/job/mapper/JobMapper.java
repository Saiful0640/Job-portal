package com.jobms.job.mapper;

import com.jobms.job.dto.JobDto;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;
import com.jobms.job.model.Job;

import java.util.List;

public class JobMapper {
    public static JobDto mapToJobWithCompanyDto(
            Job job, Company company, List<Review> reviews) {
        JobDto jobDTO = new JobDto();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompanyId(job.getCompanyId());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }

    public static Job mapToJob(JobDto jobDto) {
        Job job = new Job();
        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setMaxSalary(jobDto.getMaxSalary());
        job.setMinSalary(jobDto.getMinSalary());
        job.setCompanyId(jobDto.getCompanyId());
        return job;
    }
}
