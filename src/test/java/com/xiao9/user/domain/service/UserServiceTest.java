package com.xiao9.user.domain.service;

import com.xiao9.user.domain.IUserRepository;
import com.xiao9.user.domain.Role;
import com.xiao9.user.domain.User;
import com.xiao9.user.infrastruction.security.AuthoritiesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void createUser() {

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(AuthoritiesConstants.ADMIN);
        roles.add(role);

        User user = new User();
        user.setLogin("test");
        user.setNickname("ho");
        user.setEmail("ho@xiao9.com");
        user.setImageUrl("http://whatisit.it/50x50");
        user.setRoles(roles);
        userService.createUser(user);

        Optional<User> userPO = userRepository.findByUsernameOrEmail("test");
        Assertions.assertTrue(userPO.isPresent());
    }
}