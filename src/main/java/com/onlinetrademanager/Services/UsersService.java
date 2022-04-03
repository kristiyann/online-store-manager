package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Users.UserEdit;
import com.onlinetrademanager.DataTransferObjects.Users.UserList;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Services are a layer in the MVC design architecture which is used for building this application.
 * They describe the behaviour of models.
 * The current service is used to give functionality to objects of type User.
 * **/

@Service
@Transactional
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UUID insertUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        // Hashes the password, so we do not see it in the Database

        usersRepository.save(user);

        return user.getId();
    }

    public UserList findUserById(UUID id) throws NotFoundException {
        return usersRepository.findUserById(id)
                .stream()
                .filter(User::isActive)
                .map(this::convertDbObjToUserList)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User " + id + " not found."));
    }

    public List<UserList> findAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(this::convertDbObjToUserList)
                .collect(Collectors.toList());
    }

    public boolean updateUser(UserEdit toEdit) {
        boolean updated = false;

        if (toEdit.getPassword() != null) {
            toEdit.setPassword(BCrypt.hashpw(toEdit.getPassword(), BCrypt.gensalt(12)));
            // Hashes the password, so we do not see it in the Database

            User user = convertUserEditToDbObj(toEdit);

            usersRepository.save(user);

            updated = true;
        }

        return updated;
    }

//    public UserList updateUserPassword(UserPasswordEdit model) {
//        User updatedUser = usersRepository.findUserById(model.getId())
//                .stream()
//                .filter(User::isActive)
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("User " + model.getId() + " not found."));
//
//        if (model.getPassword() != null) {
//            updatedUser.setPassword(BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(12)));
//            // Hashes the password, so we do not see it in the Database
//            usersRepository.save(updatedUser);
//        }
//
//        return convertModelToUserList(updatedUser);
//    }

//    /** region helper methods **/
//
//    public User findUserByEmail(String email) throws NotFoundException {
//        return usersRepository.findUserByEmail(email)
//                .stream()
//                .filter(User::isActive)
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("User with email:" + email + " not found (Deleted or does not exist)."));
//    }
//
//    public User authUser(UserAuth userAuth) {
//        User user = findUserByEmail(userAuth.getEmail());
//
//        if (BCrypt.checkpw(userAuth.getPassword(), user.getPassword()) && user.isActive()) {
//            return user;
//        }
//        else return null;
//    } // !! Moved to BaseUsersService

    /** region Converter methods **/

    private UserList convertDbObjToUserList(User user) {
        UserList userList = new UserList();

        userList.setId(user.getId());
        userList.setUsername(user.getUsername());
        userList.setEmail(user.getEmail());
        userList.setActive(user.isActive());
        userList.setRole(user.getRole());
        userList.setDtype(user.getDtype());

        return userList;
    }

//    private User convertUserListToModel(UserList model) {
//        User user = new User();
//
//        user.setId(model.getId());
//        user.setUsername(model.getUsername());
//        user.setEmail(model.getEmail());
//        user.setActive(model.isActive());
//        user.setRole(model.getRole());
//
//        return user;
//    }

    private User convertUserEditToDbObj(UserEdit model) {
        User user = new User();

        user.setId(model.getId());
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setActive(model.isActive());
        user.setRole(model.getRole());
        user.setPassword(model.getPassword());

        return user;
    }
}
