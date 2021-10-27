package com.xiao9.user.domain.service;

import com.xiao9.user.domain.IRoleRepository;
import com.xiao9.user.domain.IUserRepository;
import com.xiao9.user.domain.Role;
import com.xiao9.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final IRoleRepository roleRepository;

    private final IUserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, IRoleRepository roleRepository, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setLogin(user.getLogin().toLowerCase());
        if(user.getEmail() != null) {
            user.setEmail(user.getEmail().toLowerCase());
        }
        String encryptedPassword = passwordEncoder.encode(RandomString.make(60));
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomString.make(20));
        user.setResetDate(LocalDateTime.now());
        user.setActivated(true);
        if(CollectionUtils.isEmpty(user.getRoles())) {
            Set<Role> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .map(roleRepository::findByRoleName)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        userRepository.save(user);
        log.debug("新建用户信息: {}", user);
        return user;
    }
}
