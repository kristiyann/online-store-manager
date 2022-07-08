package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.BaseUsers.UserAuth;
import com.onlinetrademanager.DataTransferObjects.Clients.ClientList;
import com.onlinetrademanager.DataTransferObjects.Users.UserList;
import com.onlinetrademanager.DataTransferObjects.XRefs.XRefClientsItemsList;
import com.onlinetrademanager.Enums.Users.SiteTheme;
import com.onlinetrademanager.Enums.Users.UserRole;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Models.Users.BaseUser;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Models.XRefClientsItems;
import com.onlinetrademanager.Repositories.BaseUsersRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.UUID;

@Service
@Transactional
public class BaseUsersService {
    private final BaseUsersRepository baseUsersRepository;
    private final ClientsService clientsService;

    public BaseUsersService(BaseUsersRepository baseUsersRepository, ClientsService clientsService) {
        this.baseUsersRepository = baseUsersRepository;
        this.clientsService = clientsService;
    }

    public BaseUser findUserByEmail(String email) throws NotFoundException {
        return baseUsersRepository.findBaseUserByEmail(email)
                .stream()
                .filter(BaseUser::isActive)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User with email:" + email + " not found (Deleted or does not exist)."));
    }

    public UserList authUser(UserAuth userAuth) {
        BaseUser user = findUserByEmail(userAuth.getEmail());

        if (BCrypt.checkpw(userAuth.getPassword(), user.getPassword()) && user.isActive()) {
            return this.convertDbObjToUserList(user);
        }
        else return null;
    }

    public void changePreferredTheme(UUID id, SiteTheme theme) {
        BaseUser user = baseUsersRepository.findById(id)
                .stream()
                .filter(BaseUser::isActive)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found."));

        user.setPreferredUserTheme(theme);
        baseUsersRepository.save(user);
    }

    public UserList convertDbObjToUserList(BaseUser user) {
        UserList userList = new UserList();

        userList.setId(user.getId());
        userList.setUsername(user.getUsername());
        userList.setEmail(user.getEmail());
        userList.setActive(user.isActive());
        userList.setRole(user.getRole());
        userList.setDtype(user.getDtype());
        userList.setPrefferedUserTheme(user.getPreferredUserTheme());

        return userList;
    }

}
