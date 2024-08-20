package peakSoft.repository;

import peakSoft.entity.Course;

import java.util.List;
import java.util.Set;

public interface CourseRepo {

    void saveCourse(Course course);
    List<Course>getAllCourses();
    Course getCourseById(Long id);
    void updateCourse(Long id,Course newCourse);
    void delete(Long id);
    void assignInstructorToCourse(Long instructorId,Long courseId);
    List<Course>getCoursesByCompanyId(Long companyId);
    void deleteAll(Set<Course> courses);
    Course getCourseByInstructorId(Long instID);
}
