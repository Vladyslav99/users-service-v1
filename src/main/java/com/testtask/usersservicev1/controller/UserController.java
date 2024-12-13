package com.testtask.usersservicev1.controller;

import com.testtask.usersservicev1.dto.UserDto;
import com.testtask.usersservicev1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Fetch users", description = "Fetch users from all db sources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
                    }),
    })
    public List<UserDto> getAllUsers(
            @Parameter(
                    description = "Search query to filter users.",
                    example = "name=Diana&id=6"
            )
            @RequestParam(required = false) String query) {
        return userService.collectAllUsers(query);
    }
}
