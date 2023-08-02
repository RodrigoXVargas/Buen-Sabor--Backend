package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.ModelMappers.UserMapper;
import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServicesDTOImpl<User, UserDto, UserMapper, Long> implements UserService {


    public UserServiceImpl(BaseRepository<User, Long> baseRepository, UserMapper mapper) {
        super(baseRepository, mapper);
    }
}
