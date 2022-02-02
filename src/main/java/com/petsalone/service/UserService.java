package com.petsalone.service;

import com.petsalone.model.User;

public interface UserService {

    User save(User user);

    boolean isUserAuthenticated();

}
