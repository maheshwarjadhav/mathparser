package com.petsalone.service;

import com.petsalone.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
