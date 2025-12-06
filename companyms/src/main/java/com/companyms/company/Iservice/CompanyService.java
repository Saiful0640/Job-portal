package com.companyms.company.Iservice;

import com.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    Boolean deleteByCompanyId(Long id);

    Boolean updateByCompanyId(Long id, Company company);
}
