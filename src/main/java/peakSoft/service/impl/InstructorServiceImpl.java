package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Course;
import peakSoft.entity.Instructor;
import peakSoft.repository.InstructorRepo;
import peakSoft.service.InstructorService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    @Override
    public void save(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    @Override
    public void assignToCompany(Long companyId, Instructor instructor) {
        instructorRepo.assignToCompany(companyId,instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.getAllInstructors();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.getInstructorById(id);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepo.deleteInstructor(id);
    }

    @Override
    public void update(Long id, Instructor newInstructor) {
        instructorRepo.update(id,newInstructor);
    }

    @Override
    public List<Course> getAllInstructorsCourses(Long id) {
        return instructorRepo.getAllInstructorsCourses(id);
    }

    @Override
    public List<Instructor> getInstructorsByCompanyId(Long companyId) {
        return instructorRepo.getInstructorsByCompanyId(companyId);
    }

    @Override
    public void deleteAll(Set<Instructor> instructors) {
            instructorRepo.deleteAll(instructors);
    }
}
