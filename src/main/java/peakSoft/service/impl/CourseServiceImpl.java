package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Course;
import peakSoft.repository.CourseRepo;
import peakSoft.service.CourseService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    @Override
    public void saveCourse(Course course) {
        courseRepo.saveCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.getCourseById(id);
    }

    @Override
    public void updateCourse(Long id, Course newCourse) {
        courseRepo.updateCourse(id, newCourse);
    }

    @Override
    public void delete(Long id) {
        courseRepo.delete(id);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        courseRepo.assignInstructorToCourse(instructorId,courseId);
    }

    @Override
    public List<Course> getCoursesByCompanyId(Long companyId) {
        return courseRepo.getCoursesByCompanyId(companyId);
    }

    @Override
    public void deleteAll(Set<Course> courses) {
        courseRepo.deleteAll(courses);
    }

    @Override
    public Course getCourseByInstructorId(Long instID) {
        return courseRepo.getCourseByInstructorId(instID);
    }


}
