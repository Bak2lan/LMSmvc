package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Student;
import peakSoft.repository.StudentRepo;
import peakSoft.service.StudentService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void saveStudentToGroup(Long groupId, Student student) {
        studentRepo.saveStudentToGroup(groupId,student);
    }

    @Override
    public List<Student> gatAllStudent() {
        return studentRepo.gatAllStudent();
    }

    @Override
    public Student getByIdStudent(Long id) {
        return studentRepo.getByIdStudent(id);
    }

    @Override
    public void deleteStudent(Long id) {
studentRepo.deleteStudent(id);
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        studentRepo.updateStudent(id,newStudent);
    }
}
