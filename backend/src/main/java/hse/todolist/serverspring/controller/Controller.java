package hse.todolist.serverspring.controller;

import hse.todolist.serverspring.auth.AuthenticationService;
import hse.todolist.serverspring.forms.UserLoginPasswordForm;
import hse.todolist.serverspring.forms.PasswordForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@ComponentScan("hse.todolist")
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class Controller {

    private final AuthenticationService authenticationService;

    @PostMapping("/test")
    ResponseEntity<String> testResponse() {
        return ResponseEntity.ok("Authorized");
    }

    @PostMapping("/changePassword")
    ResponseEntity<UserLoginPasswordForm> changePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody PasswordForm passwordForm) {
        var result = authenticationService.changePassword(token, passwordForm.getPassword());
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }

}