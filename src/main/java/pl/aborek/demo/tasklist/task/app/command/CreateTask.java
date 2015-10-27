package pl.aborek.demo.tasklist.task.app.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.user.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CreateTask {

    private final String userLogin;
    private final String taskContent;
    private final Set<String> tags;

    @JsonCreator
    public CreateTask(@JsonProperty(value = "userLogin", required = true) String userLogin,
                      @JsonProperty(value = "taskContent", required = true) String taskContent) {
        this.userLogin = userLogin;
        this.taskContent = taskContent;
        this.tags = new HashSet<>();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public Task getTask(User owner) {
        return new Task(taskContent, owner);
    }

    public CreateTask addTag(String tag) {
        tags.add(tag);
        return this;
    }

    public Stream<String> tags() {
        return tags.stream();
    }
}
