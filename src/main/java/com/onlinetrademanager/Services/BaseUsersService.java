package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.BaseUsers.UserAuth;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Models.Users.BaseUser;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.BaseUsersRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BaseUsersService {
    private final BaseUsersRepository baseUsersRepository;

    public BaseUsersService(BaseUsersRepository baseUsersRepository) {
        this.baseUsersRepository = baseUsersRepository;
    }

    public BaseUser findUserByEmail(String email) throws NotFoundException {
        return baseUsersRepository.findBaseUserByEmail(email)
                .stream()
                .filter(BaseUser::isActive)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User with email:" + email + " not found (Deleted or does not exist)."));
    }

    public BaseUser authUser(UserAuth userAuth) {
        BaseUser user = findUserByEmail(userAuth.getEmail());

        if (BCrypt.checkpw(userAuth.getPassword(), user.getPassword()) && user.isActive()) {
            return user;
        }
        else return null;
    }
}
