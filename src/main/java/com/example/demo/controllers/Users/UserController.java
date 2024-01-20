package com.example.demo.controllers.Users;

import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/get-user")
    @ResponseBody
    //    Test
    public UserEntity getById(@RequestParam Integer id){
        return userService.getById(id);
    }

    @GetMapping("/get-top-three")
    @ResponseBody
    public List<TopThreeUsersDTO> getTopThree() {
        return userService.getTopThree();
    }
}
