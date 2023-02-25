package co.com.vulky.infraestructure.data.repository.impl;

import co.com.vulky.domain.core.User;
import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.data.entity.UserEntity;
import co.com.vulky.infraestructure.data.repository.UserRepository;
import co.com.vulky.util.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getUserById(UUID userId) throws AppVulkyException {
        UserEntity user = UserEntity.builder().id(UUID.randomUUID()).firtsName("sebas").lastName("pelaez").age(28).build();
        return ObjectMapper.map(user, User.class);
    }

    @Override
    public List<User> getUsersByName(String name) throws AppVulkyException {
        List<UserEntity> listUsers = new ArrayList();
        listUsers.add(UserEntity.builder().id(UUID.randomUUID()).firtsName("sebas").lastName("pelaez").age(28).build());
        listUsers.add( UserEntity.builder().id(UUID.randomUUID()).firtsName("esteban").lastName("caro").age(25).build());
        return ObjectMapper.mapAll(listUsers, User.class);
    }

    @Override
    public User createUser(User user) throws AppVulkyException {
        user.setId(UUID.randomUUID());
        return user;
    }

    @Override
    public Boolean updateUser(User user) throws AppVulkyException {
        return false;
    }

    @Override
    public Boolean updateAgeUser(UUID userId, Integer age) throws AppVulkyException {
        return true;
    }

    @Override
    public Boolean deleteUser(UUID userId) throws AppVulkyException {
        return false;
    }

}
