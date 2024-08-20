package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Lesson;
import peakSoft.repository.LessonRepo;
import peakSoft.service.LessonService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    @Override
    public void saveToCourse(Long courseId, Lesson lesson) {
        lessonRepo.saveToCourse(courseId,lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepo.getAllLessons();
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepo.getLessonById(lessonId);
    }

    @Override
    public void updateLesson(Long id, Lesson newLesson) {
            lessonRepo.updateLesson(id,newLesson);
    }

    @Override
    public void deleteLesson(Long id) {
            lessonRepo.deleteLesson(id);
    }
}
