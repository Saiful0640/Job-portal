package com.jobms.job.messaging;

import com.jobms.job.dto.CompanyMessage;
import com.jobms.job.repository.JobRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobMessageConsumer {
    private final JobRepository jobRepository;

    public JobMessageConsumer(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RabbitListener(queues = "companyDeleteQueue")
    @Transactional
    public void consumeMessage(CompanyMessage companyMessage) {
        try {
            jobRepository.deleteByCompanyId(companyMessage.getId());
        } catch (Exception e) {
            // Log error or handle retries
            e.printStackTrace();
        }
    }
}
