package com.xiao9.user.domain;

import java.util.Optional;

public interface IRoleRepository {

    Optional<Role> findByRoleName(String role);
}
