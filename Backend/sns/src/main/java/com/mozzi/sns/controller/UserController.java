package com.mozzi.sns.controller;

import com.mozzi.sns.controller.request.UserJoinRequest;
import com.mozzi.sns.controller.request.UserLoginRequest;
import com.mozzi.sns.controller.response.Response;
import com.mozzi.sns.controller.response.UserJoinResponse;
import com.mozzi.sns.controller.response.UserLoginResponse;
import com.mozzi.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        return Response.success(UserJoinResponse.fromUser(userService.join(request.getUserName(), request.getPassword())));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request){
        return Response.success(new UserLoginResponse(userService.login(request.getUserName(), request.getPassword())));
    }

}