package peakSoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peakSoft.entity.Lesson;
import peakSoft.entity.Task;
import peakSoft.repository.TaskRepo;


import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class TaskRepoImpl implements TaskRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveTaskLesson(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.getTasks().add(task);
        task.setLesson(lesson);
        entityManager.persist(task);
    }

    @Override
    public List<Task> getAllTask() {
        return entityManager.createQuery("select t from Task t",Task.class)
                .getResultList();
    }

    @Override
    public void updateTask(Long id, Task newTask) {
        Task task = entityManager.find(Task.class, id);
        task.setTaskName(newTask.getTaskName());
        task.setTaskText(newTask.getTaskText());
        task.setDeadLine(newTask.getDeadLine());
    }

    @Override
    public void deleteTask(Long id) {
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }

    @Override
    public Task getTaskByID(Long id) {
        return entityManager.find(Task.class,id);
    }
}
