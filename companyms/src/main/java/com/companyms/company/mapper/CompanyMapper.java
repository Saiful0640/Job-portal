package com.companyms.company.mapper;

import com.companyms.company.dto.CompanyDto;
import com.companyms.company.model.Company;

public class CompanyMapper {

    public static CompanyDto mapToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setDescription(company.getDescription());
        return dto;
    }

    public static Company mapToCompany(CompanyDto dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setDescription(dto.getDescription());
        return company;
    }
}
