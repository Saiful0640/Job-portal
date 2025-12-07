package com.companyms.company.serviceImpl;

import com.companyms.company.Iservice.CompanyService;
import com.companyms.company.exceptionHandler.CompanyNotFoundException;
import com.companyms.company.model.Company;
import com.companyms.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteByCompanyId(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
        companyRepository.delete(company);
    }

    @Override
    public void updateByCompanyId(Long id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());
        companyRepository.save(existingCompany);
    }
}
