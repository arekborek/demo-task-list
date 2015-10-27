package pl.aborek.demo.tasklist.user.app;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.aborek.demo.tasklist.user.app.command.CreateUser;
import pl.aborek.demo.tasklist.user.domain.User;
import pl.aborek.demo.tasklist.user.persistance.UserRepository;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(CreateUser createUser) {
        return repository.save(createUser.getUser());
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User update(String login, String firstName, String lastName) {
        User user = repository.findOne(login);
        if (!isNullOrEmpty(firstName)) {
            user.setFirstName(firstName);
        }
        if (!isNullOrEmpty(lastName)) {
            user.setLastName(lastName);
        }
        return repository.save(user);
    }
}
