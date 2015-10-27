package pl.aborek.demo.tasklist.user.app.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.aborek.demo.tasklist.user.domain.User;

public class CreateUser {

    private final String firstName;
    private final String lastName;

    @JsonCreator
    public CreateUser(@JsonProperty(value = "firstName", required = true) String firstName,
                      @JsonProperty(value = "lastName", required = true) String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User getUser() {
        return new User(String.format("%s_%s", firstName.toLowerCase(), lastName.toLowerCase()), firstName, lastName);
    }
}
