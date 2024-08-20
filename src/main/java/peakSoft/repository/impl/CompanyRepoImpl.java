package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peakSoft.entity.Company;
import peakSoft.exception.MyException;
import peakSoft.repository.CompanyRepo;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class CompanyRepoImpl implements CompanyRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveCompany(Company company) {
        try{
            List<Company> companies = entityManager.createQuery("select c from Company c", Company.class).getResultList();
            for (Company company1:companies){
                if (company1.getName().equals(company.getName())){
                    throw new MyException("Company with this name is already exist");
                }
            }
            entityManager.persist(company);

        }catch (MyException e){
            System.err.println("Error occurred while saving company: " + e.getMessage());
        }
    }

    @Override
    public List<Company> getAllCompany() {
        return entityManager.createQuery("select c from Company c",Company.class).getResultList();
    }

    @Override
    public Company getById(Long id) {
        try{
            Company company = entityManager.find(Company.class, id);
            if (company== null){
                throw new MyException("Not found Company");
            }
            else return company;
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCompany(Long id, Company newCompany) {
        try{
            Company company = entityManager.find(Company.class, id);
            if (company==null){
                throw new MyException("Not found Company");
            }
            else company.setName(newCompany.getName());
            company.setCountry(newCompany.getCountry());
            company.setAddress(newCompany.getAddress());
            company.setPhoneNumber(newCompany.getPhoneNumber());
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCompany(Long id) {
        try{
            Company company = entityManager.find(Company.class, id);
            if (company==null){
                throw new MyException("Not found");
            }else {
            entityManager.remove(company);}
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }
}
