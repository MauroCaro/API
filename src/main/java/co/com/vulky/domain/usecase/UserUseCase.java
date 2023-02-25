package co.com.vulky.domain.usecase;

import co.com.vulky.domain.core.User;

public class UserUseCase {

    // Aquí validamos nuestro user que cumpla con las condiciones minimas para guardar
    public Boolean validateUser(User user) {
        if (user.getFirstName() == null) {
            return false;
        }
        return true;
    }

}
