package com.jobms.job.service;



import com.jobms.job.dto.JobDto;
import com.jobms.job.model.Job;

import java.util.List;

public interface JobService {

    List<Job> findAllJob();
    void saveJob(Job job);
    Job findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id,Job updateJob);
}
