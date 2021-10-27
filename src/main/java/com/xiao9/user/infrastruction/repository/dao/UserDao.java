package com.xiao9.user.infrastruction.repository.dao;

import com.xiao9.user.infrastruction.po.UserPO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserPO, Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<UserPO> findOneByLogin(String login);

    @EntityGraph(attributePaths = "roles")
    Optional<UserPO> findOneByEmailIgnoreCase(String email);
}
