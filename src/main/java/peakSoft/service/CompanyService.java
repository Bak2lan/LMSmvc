package peakSoft.service;

import org.springframework.stereotype.Service;
import peakSoft.entity.Company;

import java.util.List;

public interface CompanyService {
    void saveCompany(Company company);
    List<Company> getAllCompany();
    Company getById(Long id);
    void updateCompany(Long id, Company newCompany);
    void deleteCompany(Long id);
}
