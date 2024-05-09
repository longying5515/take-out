package com.zyt.service;

import com.zyt.dto.UserLoginDTO;
import com.zyt.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
