package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Company;
import peakSoft.repository.CompanyRepo;
import peakSoft.service.CompanyService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;


    @Override
    public void saveCompany(Company company) {
        companyRepo.saveCompany(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepo.getAllCompany();
    }

    @Override
    public Company getById(Long id) {
        return companyRepo.getById(id);
    }

    @Override
    public void updateCompany(Long id, Company newCompany) {
companyRepo.updateCompany(id,newCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepo.deleteCompany(id);
    }
}
