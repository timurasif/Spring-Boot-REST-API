package com.example.demo.controllers.Users;

import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.services.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/get-user")
    @Operation(summary = "Get user by id.")
    @ResponseBody
    public UserEntity getById(@RequestParam Integer id){
        return userService.getById(id);
    }

    @GetMapping("/get-top-three")
    @Operation(summary = "Get the top 3 users who ordered the most products based on the number of all products in the orders.")
    @ResponseBody
    public List<TopThreeUsersDTO> getTopThree() {
        return userService.getTopThree();
    }
}
