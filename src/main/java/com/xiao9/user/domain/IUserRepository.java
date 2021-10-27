package com.xiao9.user.domain;

import java.util.Optional;

public interface IUserRepository {


    Optional<User> findByUsernameOrEmail(String login);

    void save(User user);
}
