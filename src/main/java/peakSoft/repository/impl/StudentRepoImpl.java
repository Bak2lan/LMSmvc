package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peakSoft.entity.Group;
import peakSoft.entity.Student;
import peakSoft.exception.MyException;
import peakSoft.repository.StudentRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Student student) {
        if (student.getId()==null){
            entityManager.persist(student);

        }else {
            entityManager.merge(student);
        }
    }

    @Override
    public void saveStudentToGroup(Long groupId, Student student) {
        try {
            Group group = entityManager.find(Group.class, groupId);
            if (group == null) {
                throw new MyException("Not found");
            } else {
                group.getStudents().add(student);
                entityManager.persist(student);
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> gatAllStudent() {
        return entityManager.createQuery("select s from Student s ", Student.class)
                .getResultList();
    }

    @Override
    public Student getByIdStudent(Long id) {
        try {
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                throw new MyException(
                        "Not found student"
                );
            } else return student;
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

        entityManager.createQuery("delete from Student s where s.id=?1")
                .setParameter(1, id).executeUpdate();
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        Student student = entityManager.find(Student.class, id);
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setPhoneNumber(newStudent.getPhoneNumber());
        student.setEmail(newStudent.getEmail());
        student.setStudyFormat(newStudent.getStudyFormat());

    }
}
