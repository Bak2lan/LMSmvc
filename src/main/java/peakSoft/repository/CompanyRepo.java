package peakSoft.repository;

import org.springframework.stereotype.Repository;
import peakSoft.entity.Company;

import java.util.List;


public interface CompanyRepo {
    void saveCompany(Company company);
    List<Company>getAllCompany();
    Company getById(Long id);
    void updateCompany(Long id, Company newCompany);
    void deleteCompany(Long id);

}
