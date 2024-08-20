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
import peakSoft.repository.CourseRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCourse(Course course) {
       if (course.getId()==null){
           entityManager.persist(course);
       }else {
           entityManager.merge(course);
       }
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c",Course.class)
                .getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        try{
            Course course = entityManager.find(Course.class, id);
            if (course==null){
                throw new MyException("Not found Course");
            }
            else return course;
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCourse(Long id, Course newCourse) {
        try{
            Course course = entityManager.find(Course.class, id);
            if (course==null){
                throw new MyException("Not found course");
            }else {
                course.setCourseName(newCourse.getCourseName());
                course.setDescription(newCourse.getDescription());
                course.setDateOfStart(newCourse.getDateOfStart());
                course.setLessons(newCourse.getLessons());}
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
            Course course = entityManager.find(Course.class,id);
            entityManager.remove(course);


    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {

            Instructor instructor = entityManager.createQuery("select i from Instructor i inner join i.companies ic where i.id=:instructorId", Instructor.class)
                    .setParameter("instructorId", instructorId)
                    .getSingleResult();
        List<Course> courses = entityManager.createQuery("select c from Course c inner join c.company where c.id=:courseId ", Course.class)
                .setParameter("courseId", courseId)
                .getResultList();
        Set<Course>courses1= new HashSet<>(courses);
        instructor.setCourses(courses1);
        courses.get(0).setInstructor(instructor);

    }

    @Override
    public List<Course> getCoursesByCompanyId(Long companyId) {
        return entityManager.createQuery("select c from Course c join c.company cc where cc.id=:companyId", Course.class)
                .setParameter("companyId", companyId)
                .getResultList();

    }

    @Override
    public void deleteAll(Set<Course> courses) {
        entityManager.remove(courses);
    }

    @Override
    public Course getCourseByInstructorId(Long instID) {
        return entityManager.createQuery("select c from Course c join c.instructor ci where ci.id=:instID",Course.class)
                .setParameter("instID",instID)
                .getSingleResult();
    }
}
