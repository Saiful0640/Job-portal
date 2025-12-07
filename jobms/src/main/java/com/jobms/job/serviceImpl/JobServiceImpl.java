package com.jobms.job.serviceImpl;

import com.jobms.job.exceptionHandler.JobNotFoundException;
import com.jobms.job.model.Job;
import com.jobms.job.repository.JobRepository;
import com.jobms.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public void saveJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        return job;
    }

    @Override
    public void deleteJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
    }

    @Override
    public void updateJob(Long id, Job updatedJob) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setMaxSalary(updatedJob.getMaxSalary());
        existingJob.setMinSalary(updatedJob.getMinSalary());
        existingJob.setCompanyId(updatedJob.getCompanyId());

        jobRepository.save(existingJob);
    }

}
