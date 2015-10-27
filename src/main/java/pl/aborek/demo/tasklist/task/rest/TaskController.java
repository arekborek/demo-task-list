package pl.aborek.demo.tasklist.task.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.aborek.demo.tasklist.task.app.TaskService;
import pl.aborek.demo.tasklist.task.app.command.CreateTask;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.task.domain.TaskState;

import javax.websocket.server.PathParam;
import java.util.List;

import static pl.aborek.demo.tasklist.util.PageRequestUtil.createPageRequest;

@Controller
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<Task>> list(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        return new ResponseEntity(taskService.findAll(createPageRequest(pageSize, pageNumber)), HttpStatus.OK);
    }

    @RequestMapping(path = "/todo", method = RequestMethod.GET)
    public HttpEntity<Task> listTaskTodo() {
        return new ResponseEntity(taskService.findByState(TaskState.TO_DO), HttpStatus.OK);
    }

    @RequestMapping(path = "/done", method = RequestMethod.GET)
    public HttpEntity<Task> listTaskDone() {
        return new ResponseEntity(taskService.findByState(TaskState.DONE), HttpStatus.OK);
    }

    @RequestMapping(path = "/task/{taskId}", method = RequestMethod.GET)
    public HttpEntity<Task> find(@PathVariable Long taskId) {
        return new ResponseEntity(taskService.findById(taskId), HttpStatus.OK);
    }

    @RequestMapping(path = "/tag/{tag}", method = RequestMethod.GET)
    public HttpEntity<Task> find(@PathVariable String tag) {
        return new ResponseEntity(taskService.findByTag(tag), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Task> create(@RequestBody CreateTask createTask) {
        return new ResponseEntity(taskService.create(createTask), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/task/{taskId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/task/{taskId}", method = RequestMethod.PUT)
    public HttpEntity<Task> update(@PathVariable Long taskId, @PathParam("taskContent") String taskContent) {
        return new ResponseEntity(taskService.update(taskId, taskContent), HttpStatus.OK);
    }

    @RequestMapping(path = "/task/{taskId}/markastodo", method = RequestMethod.PUT)
    public HttpEntity<Task> markAsTodo(@PathVariable Long taskId) {
        return new ResponseEntity(taskService.markAsTodo(taskId), HttpStatus.OK);
    }

    @RequestMapping(path = "/task/{taskId}/markasdone", method = RequestMethod.PUT)
    public HttpEntity<Task> markAsDone(@PathVariable Long taskId) {
        return new ResponseEntity(taskService.markAsDone(taskId), HttpStatus.OK);
    }

    @RequestMapping(path = "/task/{taskId}/tag/{tag}", method = RequestMethod.PUT)
    public HttpEntity<Task> addTagToTask(@PathVariable Long taskId, @PathVariable String tag) {
        return new ResponseEntity(taskService.addTagToTask(taskId, tag), HttpStatus.OK);
    }

}
