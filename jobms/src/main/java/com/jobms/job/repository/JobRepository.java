package com.jobms.job.repository;

import com.jobms.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    void deleteByCompanyId(Long companyId);
}
