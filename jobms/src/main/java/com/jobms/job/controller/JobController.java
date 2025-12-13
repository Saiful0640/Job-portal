package com.jobms.job.controller;

import com.jobms.job.dto.JobDto;
import com.jobms.job.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> findAllJob() {
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    private static final String JOB_SAVED = "Job Saved";
    private static final String JOB_DELETED = "Job deleted";
    private static final String JOB_UPDATED = "Job updated";

    @PostMapping
    public ResponseEntity<String> create(@RequestBody JobDto jobDto) {
        jobService.createJob(jobDto);
        return ResponseEntity.ok(JOB_SAVED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobsById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.findJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        jobService.deleteJobById(id);
        return ResponseEntity.ok(JOB_DELETED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody JobDto jobDto) {
        jobService.updateJob(id, jobDto);
        return ResponseEntity.ok(JOB_UPDATED);
    }
}
