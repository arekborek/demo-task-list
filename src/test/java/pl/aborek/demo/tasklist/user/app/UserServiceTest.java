package pl.aborek.demo.tasklist.user.app;

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
import pl.aborek.demo.tasklist.user.app.command.CreateUser;
import pl.aborek.demo.tasklist.user.domain.User;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoTaskListApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void mustListAllUsers() {
        //given

        //when
        Page<User> users = userService.findAll(new PageRequest(0, 1));

        //then
        assertThat(users.getTotalElements()).isEqualTo(2);
        assertThat(users.getTotalPages()).isEqualTo(2);
    }

    @Test
    public void mustListAddUser() {
        //given

        //when
        User user = userService.create(new CreateUser("firstName", "secondName"));

        //then
        assertThat(userService.findAll()).contains(user);
    }

    @Test
    public void mustUpdateNotEmptyFirstName() {
        //given
        String newFirstName = "firstName";
        String login = "james_bond";

        //when
        User user = userService.update(login, newFirstName, null);

        //then
        assertThat(user.getFirstName()).isEqualTo(newFirstName);
        assertThat(user.getLastName()).isNotEmpty();
    }

    @Test
    public void mustUpdateNotEmptyLastName() {
        //given
        String newLastName = "lastName";
        String login = "james_bond";

        //when
        User user = userService.update(login, null, newLastName);

        //then
        assertThat(user.getLastName()).isEqualTo(newLastName);
        assertThat(user.getFirstName()).isNotNull();
    }

}