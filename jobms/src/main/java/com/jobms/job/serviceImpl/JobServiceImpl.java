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
import org.springframework.stereotype.Service;
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
        List<Long> companyIds = jobList.stream().map(Job::getCompanyId).distinct().collect(Collectors.toList());
        List<Company> companies = companyClients.getCompaniesByIds(companyIds);
        java.util.Map<Long, Company> companyMap = companies.stream().collect(Collectors.toMap(Company::getId, c -> c));

        return jobList.stream().map(job -> convertToDto(job, companyMap.get(job.getCompanyId())))
                .collect(Collectors.toList());
    }

    private JobDto convertToDto(Job job, Company company) {
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDto jobDTO = JobMapper
                .mapToJobWithCompanyDto(job, company, reviews);
        return jobDTO;
    }

    private JobDto convertToDto(Job job) {
        Company company = companyClients.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDto jobDTO = JobMapper
                .mapToJobWithCompanyDto(job, company, reviews);

        return jobDTO;
    }

    @Override
    public void createJob(JobDto jobDto) {
        // Validate company exists
        companyClients.getCompany(jobDto.getCompanyId());

        Job job = JobMapper.mapToJob(jobDto);
        jobRepository.save(job);
    }

    @Override
    public JobDto findJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        return convertToDto(job);
    }

    @Override
    public void deleteJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
    }

    @Override
    public void updateJob(Long id, JobDto updatedJob) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));

        // Validate company exists
        companyClients.getCompany(updatedJob.getCompanyId());

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setMaxSalary(updatedJob.getMaxSalary());
        existingJob.setMinSalary(updatedJob.getMinSalary());
        existingJob.setCompanyId(updatedJob.getCompanyId());

        jobRepository.save(existingJob);
    }

}
