package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peakSoft.entity.Course;
import peakSoft.entity.Lesson;
import peakSoft.exception.MyException;
import peakSoft.repository.LessonRepo;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Transactional
public class LessonRepoImpl implements LessonRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveToCourse(Long courseId, Lesson lesson) {
        Course course = entityManager.createQuery("select c from Course c where c.id=:courseId", Course.class)
                .setParameter("courseId", courseId)
                .getSingleResult();
        if (lesson.getId()==null){
            entityManager.persist(lesson);
        }else {
            entityManager.merge(lesson);
        }
        course.getLessons().add(lesson);
        lesson.setCourse(course);

    }

    @Override
    public List<Lesson> getAllLessons() {
        return entityManager.createQuery("select l from Lesson l",Lesson.class)
                .getResultList();
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        try {
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            if (lesson == null) {
                throw new MyException("not found");
            } else return lesson;
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    @Override
    public void updateLesson(Long id, Lesson newLesson) {
        try{
            Lesson lesson = entityManager.find(Lesson.class, id);
            if (lesson==null){
                throw new MyException("Not found");
            }
            else {
                lesson.setLessonName(newLesson.getLessonName());
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteLesson(Long id) {
            Lesson lesson = entityManager.find(Lesson.class, id);
            entityManager.createQuery("DELETE FROM Lesson l WHERE l.id=?1")
                    .setParameter(1,lesson.getId())
                    .executeUpdate();


    }
}
