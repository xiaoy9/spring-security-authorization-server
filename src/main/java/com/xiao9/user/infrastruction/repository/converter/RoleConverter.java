package com.xiao9.user.infrastruction.repository.converter;

import com.xiao9.user.domain.Role;
import com.xiao9.user.infrastruction.po.RolePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    Role po2Entity(RolePO po);
}
