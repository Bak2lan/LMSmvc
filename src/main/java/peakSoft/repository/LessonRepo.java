package peakSoft.repository;

import peakSoft.entity.Lesson;

import java.util.List;

public interface LessonRepo {
    void saveToCourse(Long courseId, Lesson lesson);
    List<Lesson>getAllLessons();
    Lesson getLessonById(Long lessonId);
    void updateLesson(Long id,Lesson newLesson);
    void deleteLesson(Long id);
}
