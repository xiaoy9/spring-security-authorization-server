package com.xiao9.user.infrastructure.repository.dao;

import com.xiao9.user.infrastructure.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<RolePO, String> {
}
