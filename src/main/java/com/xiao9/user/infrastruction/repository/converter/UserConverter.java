package com.xiao9.user.infrastruction.repository.converter;

import com.xiao9.user.domain.User;
import com.xiao9.user.infrastruction.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    User po2Entity(UserPO po);

    UserPO entity2PO(User entity);
}
