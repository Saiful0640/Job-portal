package com.reviewms.review.messaging;

import com.reviewms.review.dto.CompanyMessage;
import com.reviewms.review.repository.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewMessageConsumer {
    private final ReviewRepository reviewRepository;

    public ReviewMessageConsumer(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RabbitListener(queues = "companyDeleteQueue")
    @Transactional
    public void consumeMessage(CompanyMessage companyMessage) {
        try {
            reviewRepository.deleteByCompanyId(companyMessage.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
