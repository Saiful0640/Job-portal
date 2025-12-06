package com.jobms.job.dto;

import com.jobms.job.external.Company;
import com.jobms.job.external.Review;

import java.util.List;

public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String maxSalaray;
    private String minSalaray;
    private Company company;
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxSalaray() {
        return maxSalaray;
    }

    public void setMaxSalaray(String maxSalaray) {
        this.maxSalaray = maxSalaray;
    }

    public String getMinSalaray() {
        return minSalaray;
    }

    public void setMinSalaray(String minSalaray) {
        this.minSalaray = minSalaray;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
