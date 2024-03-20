package web2.atividadefinal.service;

import web2.atividadefinal.model.User;
import web2.atividadefinal.model.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    List<UserDto> findAllUsers();

    User findByEmail(String email);
}
