package com.nikhil.musicapplication.dto;

import com.nikhil.musicapplication.constant.Gender;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String phNumber;
    private int age;
    private Gender gender;
    private String username;
    private String password;
}
