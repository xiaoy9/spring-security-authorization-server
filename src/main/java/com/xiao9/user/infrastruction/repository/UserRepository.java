package com.xiao9.user.infrastruction.repository;

import com.xiao9.user.domain.IUserRepository;
import com.xiao9.user.domain.User;
import com.xiao9.user.infrastruction.repository.converter.UserConverter;
import com.xiao9.user.infrastruction.repository.dao.UserDao;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String login) {
        if(new EmailValidator().isValid(login, null)) {
            return userDao.findOneByEmailIgnoreCase(login).map(UserConverter.INSTANCE::po2Entity);
        }
        return userDao.findOneByLogin(login).map(UserConverter.INSTANCE::po2Entity);
    }

    @Override
    public void save(User user) {
        userDao.save(UserConverter.INSTANCE.entity2PO(user));
    }
}
