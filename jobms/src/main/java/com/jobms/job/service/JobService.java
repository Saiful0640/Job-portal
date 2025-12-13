package com.jobms.job.service;

import com.jobms.job.dto.JobDto;
import com.jobms.job.model.Job;

import java.util.List;

public interface JobService {

    public List<JobDto> findAllJobs();

    void createJob(JobDto jobDto);

    JobDto findJobById(Long id);

    void deleteJobById(Long id);

    void updateJob(Long id, JobDto jobDto);
}
