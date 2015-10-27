package pl.aborek.demo.tasklist.task.app;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.aborek.demo.tasklist.tag.domain.Tag;
import pl.aborek.demo.tasklist.tag.persistance.TagRepository;
import pl.aborek.demo.tasklist.task.app.command.CreateTask;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.task.domain.TaskState;
import pl.aborek.demo.tasklist.task.persistence.TaskRepository;
import pl.aborek.demo.tasklist.user.domain.User;
import pl.aborek.demo.tasklist.user.persistance.UserRepository;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task findById(Long taskId) {
        return taskRepository.findOne(taskId);
    }

    public List<Task> findByTag(String tag) {
        return taskRepository.findByTag(tagRepository.findOne(tag));
    }

    public List<Task> findByState(TaskState state) {
        return taskRepository.findByState(state);
    }

    public Task create(CreateTask createTask) {
        User owner = userRepository.findOne(createTask.getUserLogin());
        Task task = createTask.getTask(owner);
        createTask.tags().map(tagRepository::findOne).forEach(task::addTag);
        return taskRepository.save(task);
    }

    public void delete(Long taskId) {
        taskRepository.delete(taskId);
    }

    public Task update(Long taskId, String taskContent) {
        Task loaded = taskRepository.findOne(taskId);
        if (!isNullOrEmpty(taskContent)) {
            loaded.setContent(taskContent);
        }
        return taskRepository.save(loaded);
    }

    public Task markAsTodo(Long taskId) {
        Task task = taskRepository.findOne(taskId);
        task.markAsTodo();
        return taskRepository.save(task);
    }

    public Task markAsDone(Long taskId) {
        Task task = taskRepository.findOne(taskId);
        task.markAsDone();
        return taskRepository.save(task);
    }

    public Task addTagToTask(Long taskId, String tag) {
        Task task = taskRepository.findOne(taskId);
        task.addTag(tagRepository.save(new Tag(tag)));
        return taskRepository.save(task);
    }
}
