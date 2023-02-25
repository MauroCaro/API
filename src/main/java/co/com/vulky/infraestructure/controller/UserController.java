package co.com.vulky.infraestructure.controller;

import co.com.vulky.domain.core.User;
import co.com.vulky.domain.exception.AppErrorException;
import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    private Environment message;

    @Autowired
    public UserController(UserService userService, Environment message) {

        this.userService = userService;
        this.message = message;
    }

    @ResponseBody
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> findUserById(@NotNull @Valid @PathVariable UUID userId) {

        User user = null;
        try {
            user = userService.getUserById(userId);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (AppVulkyException e) {
            throw new AppErrorException(this.message.getProperty("message.error.user.find"), e);
        }

        return ResponseEntity.ok(user);
    }

    @ResponseBody
    @GetMapping(value = "/username/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getUsersByName(@NotNull @Valid @PathVariable String username) {

        List<User> user = null;
        try {
            user = userService.getUsersByName(username);
            if (user == null || user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (AppVulkyException e) {
            throw new AppErrorException(this.message.getProperty("message.error.user.find"), e);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createUser(@NotNull @Valid @RequestBody User user) {
        User newUser = User.builder().build();
        try {
            newUser = userService.createUser(user);
            if (newUser.getId().equals(UUID.fromString("0-0-0-0-0"))) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (AppVulkyException e) {
            throw new AppErrorException(this.message.getProperty("message.error.user.create"), e);
        }

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateUser(@NotNull @Valid @RequestBody User user) {

        try {
            Boolean success = userService.updateUser(user);
            if (!success) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (AppVulkyException e) {
            throw new AppErrorException(this.message.getProperty("message.error.user.update"), e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUser(@NotNull @Valid @PathVariable UUID userId) {

        try {
            Boolean success = userService.deleteUser(userId);
            if (!success) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (AppVulkyException e) {
            throw new AppErrorException(this.message.getProperty("message.error.user.delete"), e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
