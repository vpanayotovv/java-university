package com.example.automappingexercise.controllers;

import com.example.automappingexercise.dtos.UserLoginDto;
import com.example.automappingexercise.dtos.UserRegDto;
import com.example.automappingexercise.services.UserService;
import com.example.automappingexercise.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader reader;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Autowired
    public AppController(BufferedReader reader, ValidationUtil validationUtil, UserService userService) {
        this.reader = reader;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            String[] input = this.reader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":
                    if (!input[2].equals(input[3])) {
                        System.out.println("Password don't match!");
                        break;

                    } else {
                        UserRegDto userRegDto = new UserRegDto(input[1], input[2], input[4]);
                        if (this.validationUtil.isValid(userRegDto)) {
                            this.userService.registerUser(userRegDto);
                            System.out.println(input[4] +  " was registered");

                        } else {
                            this.validationUtil.getViolations(userRegDto)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    }
                    break;
                case "LoginUser":

                    UserLoginDto userLoginDto = new UserLoginDto(input[1],input[2]);
                    this.userService.loginUser(userLoginDto);
                    break;

                case "Logout":
                    this.userService.logout();
                    break;
            }
            if (input[0].equals("exit")){
                break;
            }

        }
    }
}
