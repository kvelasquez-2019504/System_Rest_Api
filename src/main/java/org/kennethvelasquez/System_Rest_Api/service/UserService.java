package org.kennethvelasquez.System_Rest_Api.service;

import org.kennethvelasquez.System_Rest_Api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity addUser(User user) ;
    ResponseEntity getAllUsers();
    ResponseEntity getUserById(Long id);
    ResponseEntity updateUser(User user, Long id);
    ResponseEntity deleteUserById(Long id);
}
