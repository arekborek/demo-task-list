package pl.aborek.demo.tasklist.user.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.aborek.demo.tasklist.user.app.command.CreateUser;
import pl.aborek.demo.tasklist.user.domain.User;
import pl.aborek.demo.tasklist.user.persistance.UserRepository;

import java.util.ArrayList;
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
        return new ArrayList<>(0);
    }

    public Page<User> findAll(Pageable pageable) {
        return new PageImpl<User>(new ArrayList<>(0));
    }

    public User update(String login, String firstName, String lastName) {
        return new User();
    }
}
