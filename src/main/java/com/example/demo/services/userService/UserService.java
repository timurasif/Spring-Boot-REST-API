package com.example.demo.services.userService;

import com.example.demo.exceptions.ExceptionHandlers;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.CreateUserRequest;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.repositories.interfaces.UsersRepoInterface;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UsersRepoInterface usersRepoInterface;

    @Cacheable(cacheNames = "user", key = "#id")
    public UserEntity getById(Integer id) {
        logger.info("User not found in cache, getting from the DB...");
        Optional<UserEntity> user = usersRepoInterface.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            logger.error("User with ID {} not found", id);
            throw new ExceptionHandlers.UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = UserEntity.builder()
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail())
                .address(createUserRequest.getAddress())
                .build();
        return usersRepoInterface.save(userEntity);
    }

    public void deleteUser(Integer id) {
        if (usersRepoInterface.existsById(id)) {
            usersRepoInterface.deleteById(id);
            logger.info("User with ID {} deleted successfully", id);
        } else {
            logger.error("User with ID {} not found", id);
            throw new ExceptionHandlers.UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public List<TopThreeUsersDTO> getTopThree(){
        return usersRepoInterface.getTopThree();
    }
}
