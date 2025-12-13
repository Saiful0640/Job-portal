package com.companyms.company.serviceImpl;

import com.companyms.company.Iservice.CompanyService;
import com.companyms.company.dto.CompanyDto;
import com.companyms.company.exceptionHandler.CompanyNotFoundException;
import com.companyms.company.mapper.CompanyMapper;
import com.companyms.company.model.Company;
import com.companyms.company.repository.CompanyRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import java.util.List;

import com.companyms.company.dto.CompanyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private RabbitTemplate rabbitTemplate;

    public CompanyServiceImpl(CompanyRepository companyRepository, RabbitTemplate rabbitTemplate) {
        this.companyRepository = companyRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
        return CompanyMapper.mapToDto(company);
    }

    @Override
    public List<CompanyDto> getCompaniesByIds(List<Long> ids) {
        return companyRepository.findAllById(ids).stream()
                .map(CompanyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.mapToCompany(companyDto);
        companyRepository.save(company);
    }

    @Override
    public void deleteByCompanyId(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
        companyRepository.delete(company);

        CompanyMessage companyMessage = new CompanyMessage();
        companyMessage.setId(company.getId());
        companyMessage.setName(company.getName());
        companyMessage.setDescription(company.getDescription());
        rabbitTemplate.convertAndSend("companyDeleteQueue", companyMessage);
    }

    @Override
    public void updateByCompanyId(Long id, CompanyDto companyDto) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("No company found:" + id));
        existingCompany.setName(companyDto.getName());
        existingCompany.setDescription(companyDto.getDescription());
        companyRepository.save(existingCompany);
    }
}
