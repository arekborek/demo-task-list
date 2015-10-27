package pl.aborek.demo.tasklist.task.app;

import org.fest.assertions.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.aborek.demo.tasklist.DemoTaskListApplication;
import pl.aborek.demo.tasklist.task.app.command.CreateTask;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.task.domain.TaskState;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoTaskListApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void mustListAllTasks() {
        //given

        //when
        List<Task> tasks = taskService.findAll();

        //then
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.size()).isEqualTo(15);
    }

    @Test
    public void mustListAllTaskWithPagination() {
        //given

        //when
        Page<Task> page = taskService.findAll(new PageRequest(0, 2));

        assertThat(page.getTotalElements()).isEqualTo(15);
        assertThat(page.getTotalPages()).isEqualTo(8);
    }

    @Test
    public void mustFindTaskById() {
        //given
        Long taskId = 1L;

        //when
        Task task = taskService.findById(taskId);

        //then
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(taskId);
    }

    @Test
    public void mustFindTaskByTag() {
        //given
        String tag = "third";

        //when
        List<Task> tasks = taskService.findByTag(tag);

        //then
        assertThat(tasks.size()).isEqualTo(2);
    }

    @Test
    public void mustFindTaskByState() {
        //given
        TaskState state = TaskState.TO_DO;

        //when
        List<Task> doneTasks = taskService.findByState(state);

        //then
        assertThat(doneTasks.size()).isEqualTo(15);
    }

    @Test
    public void mustCreateTask() {
        //given
        String taskContent = "test";
        CreateTask task = new CreateTask("james_bond", taskContent).addTag("third");

        //when
        Task created = taskService.create(task);

        //then
        assertThat(created).isNotNull();
        assertThat(created.getContent()).isEqualTo(taskContent);
    }

    @Test
    public void mustDeleteTask() {
        //given
        Long taskId = 1L;

        //when
        taskService.delete(taskId);

        //then
        assertThat(taskService.findAll().size()).isEqualTo(14);
    }

    @Test
    public void mustUpdateNotEmptyTaskContent() {
        //given
        Long taskId = 1L;
        String taskContent = "test1";

        //when
        Task updated = taskService.update(taskId, taskContent);

        //then
        assertThat(updated).isNotNull();
        assertThat(updated.getContent()).isEqualTo(taskContent);
    }

    @Test
    public void mustNotUpdateWhenTaskContentIsEmpty() {
        //given
        Long taskId = 1L;

        //when
        Task updated = taskService.update(taskId, "");

        assertThat(updated).isNotNull();
        assertThat(updated.getContent()).isNotEmpty();
    }

//    @Test
//    public void mustMarkTaskAsTodo() {
//        Fail.fail("add this test");
//    }

    @Test
    public void mustMarkTaskAsDone() {
        //given

        //when
        Task task = taskService.markAsDone(1L);

        //then
        assertThat(task.getState()).isEqualTo(TaskState.DONE);
    }

//    @Test
//    public void mustAddTagToTask() {
//        Fail.fail("add this test");
//    }

}