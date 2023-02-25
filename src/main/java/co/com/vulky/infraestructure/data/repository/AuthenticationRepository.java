package co.com.vulky.infraestructure.data.repository;

import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.data.entity.AuthenticationEntity;

public interface AuthenticationRepository {

    AuthenticationEntity getUserByUsername(String username) throws AppVulkyException;

}
