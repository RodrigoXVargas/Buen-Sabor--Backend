package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> result = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for (User user : list) {
            UserDto userDto = UserDto.builder().firstName(user.getFirstName()).lastName(user.getLastName()).blacklist(user.getBlacklist()).rol(user.getRol()).password(user.getPassword()).mail(user.getMail()).build();
            result.add(userDto);
        }
        return result;
    }

    public UserDto getUser(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        if(!(user.isEmpty())) {
            return  UserDto.builder().firstName(user.get().getFirstName()).lastName(user.get().getLastName()).blacklist(user.get().getBlacklist()).rol(user.get().getRol()).password(user.get().getPassword()).mail(user.get().getMail()).build();

        }
        return null;
    }

    public User updateUser(User user, Long userID) {
        Optional<User> userList = userRepository.findById(userID);
        if (userList.get().getId_user() == user.getId_user()) {
            log.info(String.format("udpate >>>{0}",user));
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUserById(Long userID) {
        userRepository.deleteById(userID);
    }}
