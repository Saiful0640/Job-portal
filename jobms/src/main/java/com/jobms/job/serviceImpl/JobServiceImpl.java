package com.jobms.job.serviceImpl;

import com.jobms.job.clients.CompanyClients;
import com.jobms.job.clients.ReviewClient;
import com.jobms.job.dto.JobDto;
import com.jobms.job.exceptionHandler.JobNotFoundException;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;
import com.jobms.job.mapper.JobMapper;
import com.jobms.job.model.Job;
import com.jobms.job.repository.JobRepository;
import com.jobms.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

   private JobRepository jobRepository;
   private CompanyClients companyClients;
   private ReviewClient reviewClient;


    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClients companyClients, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClients = companyClients;
        this.reviewClient = reviewClient;
    }


    @Override
    public List<JobDto> findAllJobs() {
        List<Job> jobList = jobRepository.findAll();


        return jobList.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobDto convertToDto(Job job){


        Company company = companyClients.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDto jobDTO = JobMapper
                .mapToJobWithCompanyDto(job,company,reviews);


        return jobDTO;
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
