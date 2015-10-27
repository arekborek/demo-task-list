package pl.aborek.demo.tasklist.tag.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aborek.demo.tasklist.tag.domain.Tag;
import pl.aborek.demo.tasklist.task.domain.Task;

public interface TagRepository extends JpaRepository<Tag, String> {

}
