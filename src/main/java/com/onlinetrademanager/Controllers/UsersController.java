package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.Dtos.Users.UserAuth;
import com.onlinetrademanager.Dtos.Users.UserList;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controllers are the most exposed layer of the MVC architecture which is used when creating this application.
 * With them, we can access data in the database and fetch, add, modify, or delete it.
 * Controllers create addresses which are used by the front-end part of the application.
 * They follow the format: http://localhost:8080/{table name}.
 * Spring applications run on the 8080 port.
 * Controllers' addresses redirect and trigger methods from the Service layer
 * We can pass parameters in two ways:
 * @PathVariable - requires the parameters to be set in the address string
 * @RequestBody - requires the parameters to be set in the JSON body
 * The current controller is used for operations on User.
 * **/

@RestController
@RequestMapping("/api/User")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<UserList> getUserById(@RequestParam UUID id) {
        UserList user = usersService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<UserList>> getUserList() {
        List<UserList> users = usersService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/Auth")
    public ResponseEntity<UUID> getUserByUserDetails(@RequestBody UserAuth userAuth) {
        User user = usersService.authUser(userAuth);

        if (user != null){
            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<UUID> insertUser(@RequestBody User user) {
        UUID userId = usersService.insertUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

//    @PutMapping("/UpdatePassword")
//    public ResponseEntity<UserList> updateUserPassword(@RequestBody UserPasswordEdit user) {
//        UserList updatedUser = usersService.updateUserPassword(user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
}
