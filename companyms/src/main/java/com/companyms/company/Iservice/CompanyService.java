package com.companyms.company.Iservice;

import com.companyms.company.dto.CompanyDto;
import com.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Long id);

    List<CompanyDto> getCompaniesByIds(List<Long> ids);

    void createCompany(CompanyDto companyDto);

    void deleteByCompanyId(Long id);

    void updateByCompanyId(Long id, CompanyDto companyDto);
}
