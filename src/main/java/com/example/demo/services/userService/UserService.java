package com.example.demo.services.userService;

import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.repositories.interfaces.UsersRepoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserService {

    private final UsersRepoInterface usersRepoInterface;

    public UserEntity getById(Integer id){
        Optional<UserEntity> user = usersRepoInterface.findById(id);
        return user.orElse(null);
    }

    public List<TopThreeUsersDTO> getTopThree(){
        return usersRepoInterface.getTopThree();
    }
}
