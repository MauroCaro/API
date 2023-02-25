package co.com.vulky.infraestructure.data.repository;

import co.com.vulky.domain.core.User;
import co.com.vulky.domain.exception.AppVulkyException;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    public User getUserById(UUID userId) throws AppVulkyException;

    public List<User> getUsersByName(String name) throws AppVulkyException;

    public User createUser(User user) throws AppVulkyException;

    public Boolean updateUser(User user) throws AppVulkyException;

    public Boolean updateAgeUser(UUID userId, Integer age) throws AppVulkyException;

    public Boolean deleteUser(UUID userId) throws AppVulkyException;

}
