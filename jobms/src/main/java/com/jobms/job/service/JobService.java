package com.jobms.job.service;

import com.jobms.job.model.Job;

import java.util.List;

public interface JobService {

    List<Job> findAllJob();

    void saveJob(Job job);

    Job findJobById(Long id);

    void deleteJobById(Long id);

    void updateJob(Long id, Job updateJob);
}
