package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserDtoPage;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserDtoPage findAllUser(Pageable pageable);

    UserDto findUserById(UUID userId);

    UserDto create(ShortUserDto user);

    UserDto update(UserDto user);

    void delete(UUID id);

    void deleteFromBd(UUID id);
}
