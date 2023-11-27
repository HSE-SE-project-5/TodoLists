package hse.todolist.serverspring.controller;

import hse.todolist.databaseInteractor.UserService;
import hse.todolist.entities.User;
import hse.todolist.serverspring.auth.JwtService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@ComponentScan("hse.todolist")
@RequestMapping("/todolist/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/getUserInfo")
    ResponseEntity<User> getUserInfo(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtService.extractUsername(token);
        try {
            int user_id = userService.getUserIdWithLogin(username);
            return new ResponseEntity<>(userService.getUserWithUserId(user_id), HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
