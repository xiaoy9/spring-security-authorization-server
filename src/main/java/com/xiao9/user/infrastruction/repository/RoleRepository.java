package com.xiao9.user.infrastruction.repository;

import com.xiao9.user.domain.IRoleRepository;
import com.xiao9.user.domain.Role;
import com.xiao9.user.infrastruction.repository.converter.RoleConverter;
import com.xiao9.user.infrastruction.repository.dao.RoleDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository implements IRoleRepository {

    private final RoleDao roleDAO;

    public RoleRepository(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public Optional<Role> findByRoleName(String role) {
        return roleDAO.findById(role).map(RoleConverter.INSTANCE::po2Entity);
    }
}
