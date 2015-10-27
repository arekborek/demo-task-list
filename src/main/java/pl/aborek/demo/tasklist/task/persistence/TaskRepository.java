package pl.aborek.demo.tasklist.task.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.aborek.demo.tasklist.tag.domain.Tag;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.task.domain.TaskState;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where :tag member t.tags")
    List<Task> findByTag(@Param("tag") Tag tag);

    List<Task> findByState(TaskState state);

}
