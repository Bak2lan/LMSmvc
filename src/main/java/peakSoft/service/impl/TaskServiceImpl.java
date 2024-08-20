package peakSoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peakSoft.entity.Task;
import peakSoft.repository.TaskRepo;
import peakSoft.service.TaskService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    @Override
    public void saveTaskLesson(Long lessonId, Task task) {
        taskRepo.saveTaskLesson(lessonId,task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepo.getAllTask();
    }

    @Override
    public void updateTask(Long id, Task newTask) {
            taskRepo.updateTask(id,newTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteTask(id);
    }

    @Override
    public Task getTaskByID(Long id) {
        return taskRepo.getTaskByID(id);
    }
}
