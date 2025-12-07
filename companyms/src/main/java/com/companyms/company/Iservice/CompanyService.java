package com.companyms.company.Iservice;

import com.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    void deleteByCompanyId(Long id);

    void updateByCompanyId(Long id, Company company);
}
