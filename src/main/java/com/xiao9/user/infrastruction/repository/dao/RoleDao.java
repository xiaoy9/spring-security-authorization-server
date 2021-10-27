package com.xiao9.user.infrastruction.repository.dao;

import com.xiao9.user.infrastruction.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<RolePO, String> {
}
