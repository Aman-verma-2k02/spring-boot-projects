package com.amverma.user_service.service;

import com.amverma.user_service.dao.UserDao;
import com.amverma.user_service.dto.DTOMapper;
import com.amverma.user_service.dto.UserRequestDTO;
import com.amverma.user_service.dto.UserResponseDTO;
import com.amverma.user_service.entity.User;
import com.amverma.user_service.exception.UserNotFoundException;
import com.amverma.user_service.util.ApiResponse;
import com.amverma.user_service.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;


@Service
public class UserService {
    private final UserDao userDao;
    private final DTOMapper dtoMapper;

    public UserService(UserDao userDao, DTOMapper dtoMapper) {
        this.userDao = userDao;
        this.dtoMapper = dtoMapper;
    }

    public User findUserById(int id) {
        return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(MessageFormat.format(CommonConstants.USER_NOT_FOUND, id)));
    }

    public List<UserResponseDTO> addNewUsers(List<UserRequestDTO> userRequestDTOList) {
        return userRequestDTOList.stream().map((this::addNewUser)).toList();
    }

    public UserResponseDTO addNewUser(UserRequestDTO userRequestDTO) {
        User user = dtoMapper.toEntity(userRequestDTO);
        userDao.save(user);
        return dtoMapper.toDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userDao.findAll().stream().map(dtoMapper::toDTO).toList();
    }

    public UserResponseDTO getUserById(int id) {
        User user = this.findUserById(id);
        return dtoMapper.toDTO(user);
    }

    public UserResponseDTO updateUserById(int id, UserRequestDTO userRequestDTO) {
        User user = this.findUserById(id);
        dtoMapper.update(userRequestDTO, user);
        userDao.save(user);
        return dtoMapper.toDTO(user);
    }

    public UserResponseDTO deleteUserById(int id) {
        User user = this.findUserById(id);
        userDao.delete(user);
        return dtoMapper.toDTO(user);
    }
}
