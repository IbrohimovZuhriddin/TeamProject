package zuhriddinscode.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zuhriddinscode.dto.JwtAuthenticationResponse;
import zuhriddinscode.dto.SignInRequest;
import zuhriddinscode.dto.SignUpRequest;
import zuhriddinscode.dto.UserDTO;
import zuhriddinscode.model.UserEntity;
import zuhriddinscode.registrationEmailVerification.UserServiceVerification;
import zuhriddinscode.service.AuthenticationService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zuhriddinscode.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController  {
    @Autowired
    private UserServiceVerification userServiceVerification;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping ("/create-user")
    public void create(@RequestBody UserDTO userDTO){
        userService.create(userDTO);
    }

    @GetMapping("/findByName/{userDTO}")
    public List<UserDTO> findByName(@RequestParam String user_name){
        return userService.findByUserName( user_name );
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        return userServiceVerification.saveUser(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userServiceVerification.confirmEmail(confirmationToken);
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

}