package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peakSoft.entity.Company;
import peakSoft.entity.Course;
import peakSoft.entity.Instructor;
import peakSoft.exception.MyException;
import peakSoft.repository.InstructorRepo;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
@RequiredArgsConstructor
public class InstructorRepoImpl implements InstructorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Instructor instructor) {
        if (instructor.getId() == null) {
            entityManager.persist(instructor);
        } else {
            entityManager.merge(instructor);
        }

    }

    @Override
    public void assignToCompany(Long companyId, Instructor instructor) {

        Company company = entityManager.createQuery("select c from Company c where c.id=:companyId", Company.class)
                .setParameter("companyId", companyId)
                .getSingleResult();
        if (instructor.getId() == null) {

            entityManager.persist(instructor);
        } else {
            company.getInstructors().add(instructor);
            entityManager.merge(instructor);
        }
        company.getInstructors().add(instructor);
    }


    @Override
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i ", Instructor.class)
                .getResultList();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try {
            Instructor instructor = entityManager.find(Instructor.class, id);
            if (instructor == null) {
                throw new MyException("Not found");
            } else {
                return instructor;
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteInstructor(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        System.out.println("instructor = " + instructor.getFirstName());

        entityManager.createQuery(
                        "DELETE FROM Instructor i WHERE i.id = ?1"
                ).setParameter(1, instructor.getId())
                .executeUpdate();
    }

    @Override
    public void update(Long id, Instructor newInstructor) {
        try {
            Instructor instructor = entityManager.find(Instructor.class, id);
            if (instructor == null) {
                throw new MyException("Not found instructor");
            } else {
                instructor.setFirstName(newInstructor.getFirstName());
                instructor.setLastName(newInstructor.getLastName());
                instructor.setPhoneNumber(newInstructor.getPhoneNumber());
                instructor.setSpecialization(newInstructor.getSpecialization());

            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Course> getAllInstructorsCourses(Long id) {
        try {
            List<Course> courses = entityManager.createQuery("select c from Course c join c.instructor cd where cd.id=:id", Course.class)
                    .setParameter("id", id)
                    .getResultList();
            if (courses.isEmpty()){
                throw new MyException("Not found");

            }else return courses;
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

        return null
                ;
    }

    @Override
    public List<Instructor> getInstructorsByCompanyId(Long companyId) {
        return entityManager.createQuery("select i from Instructor i join i.companies ic where ic.id=:companyId",Instructor.class)
                .setParameter("companyId",companyId)
                .getResultList();
    }

    @Override
    public void deleteAll(Set<Instructor> instructors) {
        entityManager.remove(instructors);
    }
}
