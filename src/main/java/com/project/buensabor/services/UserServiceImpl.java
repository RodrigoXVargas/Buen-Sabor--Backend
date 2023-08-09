package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.UserMapper;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.repositories.UserRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.AddressService;
import com.project.buensabor.services.interfaces.OrderService;
import com.project.buensabor.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl extends BaseServicesDTOImpl<User, UserDto, UserMapper, Long> implements UserService {


    public UserServiceImpl(BaseRepository<User, Long> baseRepository, UserMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional //Indica que el método es una transacción.
    public List<UserDto> findAll() throws Exception {
        try {
            List<User> entities = userRepository.findAll();
            return getAddressesAndOrders(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public UserDto findById(Long id) throws Exception {
        try {
            Optional<User> entityOptional = userRepository.findById(id);
            UserDto entityDto = mapper.convertToDto(entityOptional.get());
            entityDto.setAddresses(addressService.addressesByUserId(entityDto.getId()));
            entityDto.setOrders(orderService.ordersByUserId(entityDto.getId()));
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserDto> findEmployees() throws Exception {
        try {
            List<User> employees = userRepository.findAllEmployees();
            return getAddressesAndOrders(employees);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public List<UserDto> getAddressesAndOrders(List<User> entities) throws Exception {
        List<UserDto> entitiesDtos = new ArrayList<>();
        for (User entity : entities) {
            entitiesDtos.add(mapper.convertToDto(entity));
        }
        for (UserDto entityDto : entitiesDtos){
            entityDto.setOrders(orderService.ordersByUserId(entityDto.getId()));
            entityDto.setAddresses(addressService.addressesByUserId(entityDto.getId()));
        }

        return entitiesDtos;
    }
}
