package com.ms.user.service;

import com.ms.user.dto.UserRequest;
import com.ms.user.dto.UserResponse;

public interface UserService {

    UserResponse create(UserRequest request);

    UserResponse findById(Long id);
}