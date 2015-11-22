package pl.aborek.demo.tasklist.task.app;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import java.util.ArrayList;
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
        return new ArrayList<>(0);
    }

    public Page<Task> findAll(Pageable pageable) {
        return new PageImpl<Task>(new ArrayList<>(0));
    }

    public Task findById(Long taskId) {
        return new Task();
    }

    public List<Task> findByTag(String tag) {
        return new ArrayList<>();
    }

    public List<Task> findByState(TaskState state) {
        return new ArrayList<>();
    }

    public Task create(CreateTask createTask) {
        User owner = userRepository.findOne(createTask.getUserLogin());
        Task task = createTask.getTask(owner);
        createTask.tags().map(tagRepository::findOne).forEach(task::addTag);
        return taskRepository.save(task);
    }

    public void delete(Long taskId) {

    }

    public Task update(Long taskId, String taskContent) {
        return new Task();
    }

    public Task markAsTodo(Long taskId) {
        return new Task();
    }

    public Task markAsDone(Long taskId) {
        return new Task();
    }

    public Task addTagToTask(Long taskId, String tag) {
        return new Task();
    }
}
