package com.project.buensabor.services;

import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServicesImpl<User, Long> implements UserService {

    public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }

}
