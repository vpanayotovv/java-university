package com.example.automappingexercise.controllers;

import com.example.automappingexercise.dtos.GameAddDto;
import com.example.automappingexercise.dtos.GameEditDto;
import com.example.automappingexercise.dtos.UserLoginDto;
import com.example.automappingexercise.dtos.UserRegDto;
import com.example.automappingexercise.services.GameService;
import com.example.automappingexercise.services.UserService;
import com.example.automappingexercise.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader reader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader reader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.reader = reader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
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
                            System.out.println(input[4] + " was registered");

                        } else {
                            this.validationUtil.getViolations(userRegDto)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    }
                    break;

                case "LoginUser":

                    UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);
                    this.userService.loginUser(userLoginDto);
                    break;

                case "Logout":
                    this.userService.logout();
                    break;

                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(input[1],
                            new BigDecimal(input[2]),
                            Double.parseDouble(input[3]),
                            input[4],
                            input[5],
                            input[6], LocalDate.of(
                            Integer.parseInt(input[7].split("-")[2]),
                            Integer.parseInt(input[7].split("-")[1]),
                            Integer.parseInt(input[7].split("-")[0])));

                    try {
                        if (this.validationUtil.isValid(gameAddDto)) {
                            this.gameService.addGame(gameAddDto);
                            System.out.println("Added " + input[1]);
                        } else {
                            this.validationUtil.getViolations(gameAddDto)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("No logged user!");
                    }
                    break;

                case "EditGame":
                    try {
                        GameEditDto gameEditDto = new GameEditDto(Long.parseLong(input[1]),
                                new BigDecimal(input[2].split("=")[1]),
                                Double.parseDouble(input[3].split("=")[1]));
                        this.gameService.editGame(gameEditDto);
                    } catch (NullPointerException ex) {
                        System.out.println("No logged user!");
                    }
                    break;

                case "DeleteGame":
                    try {
                        Long id = Long.parseLong(input[1]);
                        this.gameService.deleteGame(id);
                    } catch (NullPointerException ex) {
                        System.out.println("No logged user!");
                    }
                    break;

                //TODO: More to implement!

            }
            if (input[0].equals("exit")) {
                break;
            }

        }
    }
}
