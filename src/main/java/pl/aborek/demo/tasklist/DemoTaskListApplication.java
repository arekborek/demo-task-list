package pl.aborek.demo.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.aborek.demo.tasklist.tag.app.TagService;
import pl.aborek.demo.tasklist.tag.app.command.CreateTag;
import pl.aborek.demo.tasklist.task.app.TaskService;
import pl.aborek.demo.tasklist.task.app.command.CreateTask;
import pl.aborek.demo.tasklist.user.app.UserService;
import pl.aborek.demo.tasklist.user.app.command.CreateUser;

@SpringBootApplication
public class DemoTaskListApplication {

    @Component
    public static class Initializer implements CommandLineRunner {

        @Autowired
        private UserService userService;

        @Autowired
        private TaskService taskService;

        @Autowired
        private TagService tagService;

        public void run(String... args) {
            userService.create(new CreateUser("Master", "Admin"));
            userService.create(new CreateUser("James", "Bond"));

            tagService.create(new CreateTag("trainer"));
            tagService.create(new CreateTag("trainee"));
            tagService.create(new CreateTag("first"));
            tagService.create(new CreateTag("second"));
            tagService.create(new CreateTag("third"));

            taskService.create(new CreateTask("master_admin", "do this training correctly").addTag("trainer").addTag("trainee").addTag("first"));
            taskService.create(new CreateTask("james_bond", "show datasource configuration").addTag("trainer").addTag("first"));
            taskService.create(new CreateTask("master_admin", "show all spring data features").addTag("trainer").addTag("second"));
            taskService.create(new CreateTask("james_bond", "support Optional for repository methods").addTag("trainer").addTag("second"));
            taskService.create(new CreateTask("james_bond", "support Stream in repository methods").addTag("trainer").addTag("second"));
            taskService.create(new CreateTask("master_admin", "support CompletableFuture in @Async repository methods").addTag("trainer").addTag("second"));
            taskService.create(new CreateTask("james_bond", "auditing feature").addTag("trainer").addTag("second"));
            taskService.create(new CreateTask("james_bond", "show lombok power @Getter, @ToString, @AllArgsConstructor").addTag("trainer").addTag("first"));
            taskService.create(new CreateTask("james_bond", "add sorting by priority in task list").addTag("trainee").addTag("second"));
            taskService.create(new CreateTask("master_admin", "add filtering to task list by TODO tasks").addTag("trainee").addTag("first"));
            taskService.create(new CreateTask("master_admin", "add mark task as undone").addTag("trainee").addTag("first"));
            taskService.create(new CreateTask("james_bond", "add pagination and sorting to list of tags").addTag("trainee").addTag("first"));
            taskService.create(new CreateTask("james_bond", "add pagination and sorting to list of users").addTag("trainee").addTag("first"));


            taskService.create(new CreateTask("james_bond", "findByTag with pagination").addTag("trainee").addTag("third"));
            taskService.create(new CreateTask("james_bond", "findByState with pagination").addTag("trainee").addTag("third"));

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoTaskListApplication.class, args);
    }
}
