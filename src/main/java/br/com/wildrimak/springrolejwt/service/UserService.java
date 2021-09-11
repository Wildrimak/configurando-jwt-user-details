package br.com.wildrimak.springrolejwt.service;

import java.util.List;

import br.com.wildrimak.springrolejwt.dto.UserDto;
import br.com.wildrimak.springrolejwt.model.User;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    User findOne(String username);

}
