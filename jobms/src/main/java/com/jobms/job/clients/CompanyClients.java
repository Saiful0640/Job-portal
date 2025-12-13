package com.jobms.job.clients;

import com.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "COMPANYMS")
public interface CompanyClients {

    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable("id") Long id);

    @PostMapping("/company/batch")
    List<Company> getCompaniesByIds(@RequestBody List<Long> ids);
}
