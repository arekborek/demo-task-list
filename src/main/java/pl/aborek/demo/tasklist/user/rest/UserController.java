package pl.aborek.demo.tasklist.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.aborek.demo.tasklist.user.app.UserService;
import pl.aborek.demo.tasklist.user.app.command.CreateUser;
import pl.aborek.demo.tasklist.user.domain.User;

import javax.websocket.server.PathParam;
import java.util.List;

import static pl.aborek.demo.tasklist.util.PageRequestUtil.createPageRequest;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<User>> list(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        return new ResponseEntity(userService.findAll(createPageRequest(pageSize, pageNumber)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<User> create(@RequestBody CreateUser createUser) {
        return new ResponseEntity(userService.create(createUser), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.PUT)
    public HttpEntity<User> update(@PathVariable String userId, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return new ResponseEntity(userService.update(userId, firstName, lastName), HttpStatus.OK);
    }

}
