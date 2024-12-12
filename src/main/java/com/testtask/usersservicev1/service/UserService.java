package com.testtask.usersservicev1.service;

import com.testtask.usersservicev1.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> collectAllUsers(String query);
}
