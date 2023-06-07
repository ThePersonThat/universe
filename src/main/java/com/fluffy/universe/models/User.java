package com.fluffy.universe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private Integer roleId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private String birthday;
    private String address;
    private String website;
    private String resetPasswordToken;
}
