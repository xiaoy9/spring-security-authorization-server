package com.xiao9.user.infrastruction.security;

import com.xiao9.user.domain.IUserRepository;
import com.xiao9.user.domain.User;
import com.xiao9.user.infrastruction.security.error.UserNotActivatedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserAuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        return userRepository.findByUsernameOrEmail(username)
                .map(user-> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(()-> new UsernameNotFoundException("用户: " + lowercaseLogin + " 在数据库中不存在！"));
    }


    private UserDetails createSpringSecurityUser(String lowercaseLogin, User user) {
        if(!user.isActivated()) {
            throw new UserNotActivatedException("用户: " + lowercaseLogin + " 处于未激活状态！");
        }
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().startsWith("ROLE_") ? authority.getName() : "ROLE_" + authority.getName()))
//                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
