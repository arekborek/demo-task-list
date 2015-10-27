package pl.aborek.demo.tasklist.user.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aborek.demo.tasklist.task.domain.Task;
import pl.aborek.demo.tasklist.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
