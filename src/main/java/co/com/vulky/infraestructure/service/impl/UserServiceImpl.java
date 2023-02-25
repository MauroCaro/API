package co.com.vulky.infraestructure.service.impl;

import co.com.vulky.domain.usecase.UserUseCase;
import co.com.vulky.domain.core.User;
import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.data.repository.UserRepository;
import co.com.vulky.infraestructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID userId) throws AppVulkyException {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getUsersByName(String name) throws AppVulkyException {
        return userRepository.getUsersByName(name);
    }

    @Override
    public User createUser(User user) throws AppVulkyException {
        UserUseCase business = new UserUseCase();
        if (!business.validateUser(user)) {
            new Throwable("error");
        }
        return userRepository.createUser(user);
    }

    @Override
    public Boolean updateUser(User user) throws AppVulkyException {
        return userRepository.updateUser(user);
    }

    public Boolean updateAgeUser(UUID userId, Integer age) throws AppVulkyException{
        return userRepository.updateAgeUser(userId, age);
    }

    @Override
    public Boolean deleteUser(UUID userId) throws AppVulkyException {
        return userRepository.deleteUser(userId);
    }

}
