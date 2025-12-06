package com.jobms.job.controller;



import com.jobms.job.model.Job;
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
    public ResponseEntity<List<Job>> findAllJob() {
        return ResponseEntity.ok(jobService.findAllJob());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Job job) {
        jobService.saveJob(job);
        return ResponseEntity.ok("Job Saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobsById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.findJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        jobService.deleteJobById(id);
        return ResponseEntity.ok("Job deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Job job) {
        jobService.updateJob(id, job);
        return ResponseEntity.ok("Job updated");
    }
}

