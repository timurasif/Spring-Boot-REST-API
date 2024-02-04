package com.example.demo.models.Users;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "User name is required")
    @Column(name = "user_name")
    private String userName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
