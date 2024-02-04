package com.example.demo.controllers.Users;

import com.example.demo.models.Response;
import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.CreateUserRequest;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.services.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService;

    @GetMapping("/get")
    @Operation(summary = "Get user by id.")
    public Response<Object> getById(@RequestParam Integer id){
        logger.info("Get User request with user Id: {}", id);
        UserEntity user = userService.getById(id);
        logger.info("Get User response: {}", user);
        return new Response(200, "Success.", user);
    }

    @PostMapping("/create")
    @Operation(summary = "Create user.")
    public Response<Object> create(@Valid @RequestBody CreateUserRequest createUserRequest){
        logger.info("Get User request with: {}", createUserRequest);
        UserEntity createUser = userService.createUser(createUserRequest);
        logger.info("User created: {}", createUser);
        return new Response(201, "User created successfully.", createUser);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete user by id.")
    public Response<Object> deleteById(@RequestParam Integer id){
        logger.info("Delete User request with user Id: {}", id);
        userService.deleteUser(id);
        return new Response(200, "Success.", null);
    }

    @GetMapping("/get-top-three")
    @Operation(summary = "Get the top 3 users who ordered the most products based on the number of all products in the orders.")
    public Response<Object> getTopThree() {
        List<TopThreeUsersDTO> list = userService.getTopThree();
        return new Response(200, "Success.", list);

    }
}
