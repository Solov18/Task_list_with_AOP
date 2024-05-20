package MyCeiling_.task_list.task_list.service;

import MyCeiling_.task_list.task_list.aspect.TrackUserAction;
import MyCeiling_.task_list.task_list.domain.Task;

import MyCeiling_.task_list.task_list.domain.TaskStatus;
import MyCeiling_.task_list.task_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static MyCeiling_.task_list.task_list.domain.TaskStatus.COMPLETED;
import static MyCeiling_.task_list.task_list.domain.TaskStatus.IN_PROGRESS;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /** Записать задачу в базу
     * @param task - задача для записи
     */
    @TrackUserAction
    public Task saveTask(Task task) {     // сохранить задачу
        return taskRepository.save(task);
    }
    @TrackUserAction
    public List<Task> getAllTasks() { // получить все задачи
        return taskRepository.findAll();
    }

    @TrackUserAction// добавить задачу
    public  Task addTask(String description, LocalDateTime deadline ) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(TaskStatus.NOT_STARTED);
        task.setCreatedDate(LocalDateTime.now());

        task.setDeadline(deadline);
        return taskRepository.save(task);
     }
    @TrackUserAction
    public void delTask(Long id) {
        if (taskRepository.existsById(id))
            taskRepository.deleteById(id);
    }
    @TrackUserAction
     public List<Task> getTaskByStatus(TaskStatus taskStatus) { // получить задачи по статусу
         return taskRepository.findByStatus(taskStatus);}

    @TrackUserAction
    public void updateStatus(Long id) {
        if (taskRepository.existsById(id)) {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                switch (task.getStatus()) {
                    case NOT_STARTED -> {
                        task.setStatus(IN_PROGRESS);
                    }
                    case IN_PROGRESS -> {
                        task.setStatus(COMPLETED);
                    }
                    default -> {
                        return;
                    }
                }
            }

            taskRepository.save(task);
        }
    }
}




