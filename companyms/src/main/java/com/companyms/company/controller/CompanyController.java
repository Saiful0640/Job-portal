package com.companyms.company.controller;

import com.companyms.company.Iservice.CompanyService;
import com.companyms.company.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<String> savecompany(@RequestBody Company company) {

        companyService.createCompany(company);
        return ResponseEntity.ok("Company Saved");
    }
    @GetMapping
    public ResponseEntity<List<Company>> getall() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanysById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        companyService.deleteByCompanyId(id);
        return ResponseEntity.ok("Job deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        companyService.updateByCompanyId(id, company);
        return ResponseEntity.ok("Job updated");
    }
}
