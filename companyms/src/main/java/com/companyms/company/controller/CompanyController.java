package com.companyms.company.controller;

import com.companyms.company.Iservice.CompanyService;
import com.companyms.company.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.companyms.company.dto.CompanyDto;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<CompanyDto>> getCompaniesByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(companyService.getCompaniesByIds(ids));
    }

    @PostMapping
    public ResponseEntity<String> savecompany(@RequestBody CompanyDto companyDto) {

        companyService.createCompany(companyDto);
        return ResponseEntity.ok("Company Saved");
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getall() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanysById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        companyService.deleteByCompanyId(id);
        return ResponseEntity.ok("Company deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        companyService.updateByCompanyId(id, companyDto);
        return ResponseEntity.ok("Company updated");
    }
}
