package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.UserMapper;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.*;
import com.project.buensabor.enums.StatusType;
import com.project.buensabor.enums.StatusUser;
import com.project.buensabor.repositories.AddressRepository;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.repositories.UserRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.AddressService;
import com.project.buensabor.services.interfaces.OrderService;
import com.project.buensabor.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl extends BaseServicesDTOImpl<User, UserDto, UserMapper, Long> implements UserService {
    @Autowired
    private AddressRepository addressRepository;


    public UserServiceImpl(BaseRepository<User, Long> baseRepository, UserMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<UserDto> findAll() throws Exception {
        try {
            List<User> entities = userRepository.findAll();
            List<UserDto> entitiesDtos = new ArrayList<>();
            for (User user: entities) {
                entitiesDtos.add(this.getAddressesAndOrders(user));
            }
            return entitiesDtos;
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
            UserDto entityDto = this.getAddressesAndOrders(entityOptional.get());
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<UserDto> findEmployees() throws Exception {
        try {
            List<User> employees = userRepository.findAllEmployees();
            List<UserDto> employeesDtos = new ArrayList<>();
            for (User user: employees) {
                employeesDtos.add(this.getAddressesAndOrders(user));
            }
            return employeesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public String changeRol(RolDto rol, Long id) throws Exception {
        try{
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.get();
            user.setRol(modelMapper.map(rol, Rol.class));
            user = userRepository.save(user);
            return "Se cambio el rol a "+ user.getRol().getRol();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public String changeBlacklist(Long id) throws Exception {
        try{
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.get();
            if (user.getBlacklist() == StatusUser.Enabled) {
                user.setBlacklist(StatusUser.Malicious);
            } else {
                user.setBlacklist(StatusUser.Enabled);
            }
            user = userRepository.save(user);
            return "Se cambio el blacklist a "+ user.getBlacklist().name();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserDto findUserByEmail(String mail) throws Exception {
        try{
            if (userRepository.existsUserByMail(mail)){
                Optional<User> userOptional = userRepository.findByEmail(mail);
                User user = userOptional.get();
                UserDto userDto = this.getAddressesAndOrders(user);
                return userDto;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public UserDto getAddressesAndOrders(User entity) throws Exception {
        UserDto entityDto = mapper.convertToDto(entity);
        entityDto.setOrders(orderService.ordersByUserId(entityDto.getId()));
        entityDto.setAddresses(addressService.addressesByUserId(entityDto.getId()));

        return entityDto;
    }



    @Override
    @Transactional
    public UserDto saveOne(UserDto entityDto) throws Exception {
        try {
            User userEntity = new User();
            modelMapper.map(entityDto, userEntity);
            userEntity = userRepository.save(userEntity);

            List<AddressWithoutuserDto> addressWithoutuserDtoList = new ArrayList<>();

            if(entityDto.getAddresses().size() != 0){
                for (AddressWithoutuserDto addressWithoutuserDto : entityDto.getAddresses()) {
                    Address address = new Address();
                    address.setUser(userEntity);
                    address.setLocation(modelMapper.map(addressWithoutuserDto.getLocation(), Location.class));
                    address.setNumber(addressWithoutuserDto.getNumber());
                    address.setStreet(addressWithoutuserDto.getStreet());
                    address = addressRepository.save(address);
                    modelMapper.map(address, addressWithoutuserDto);
                    addressWithoutuserDtoList.add(addressWithoutuserDto);
                }
            }
            entityDto = mapper.convertToDto(userEntity);
            entityDto.setAddresses(addressWithoutuserDtoList);
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public UserDto updateOne(UserDto entityDto, Long id) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User userExistente = userOptional.get();
            modelMapper.map(entityDto, userExistente);

            userExistente = userRepository.save(userExistente);


            List<AddressWithoutuserDto> addressWithoutuserDtoList = new ArrayList<>();

            if(entityDto.getAddresses().size() == 0){
                List<Address> addresses = addressRepository.findAddressesByUserId(id);
                if (addresses.size() != 0) {
                    for (Address address: addresses) {
                        addressRepository.deleteById(address.getId());
                    }
                }
            } else {
                List<Address> addresses = addressRepository.findAddressesByUserId(id);
                if (addresses.size() != 0) {
                    for (Address address: addresses) {
                        long idBuscado = 0;
                        for (AddressWithoutuserDto addressWithoutuserDto : entityDto.getAddresses()) {
                            if (Objects.nonNull(addressWithoutuserDto.getId())) {
                                if (addressWithoutuserDto.getId() == address.getId()){
                                    idBuscado = addressWithoutuserDto.getId();
                                }
                            }
                        }
                        if(idBuscado==0){
                            addressRepository.deleteById(address.getId());
                        }
                    }
                }
                for (AddressWithoutuserDto addressWithoutuserDto : entityDto.getAddresses()) {
                    Address address;
                    if(Objects.isNull(addressWithoutuserDto.getId())){
                        address = new Address();
                    } else {
                        Optional<Address> addressOptional = addressRepository.findById(addressWithoutuserDto.getId());
                        address = addressOptional.get();
                    }
                    address.setUser(userExistente);
                    address.setLocation(modelMapper.map(addressWithoutuserDto.getLocation(), Location.class));
                    address.setNumber(addressWithoutuserDto.getNumber());
                    address.setStreet(addressWithoutuserDto.getStreet());
                    address = addressRepository.save(address);
                    modelMapper.map(address, addressWithoutuserDto);
                    addressWithoutuserDtoList.add(addressWithoutuserDto);
                }
            }
            entityDto = mapper.convertToDto(userExistente);
            entityDto.setAddresses(addressWithoutuserDtoList);
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            if (userRepository.existsById(id)) {

                List<Address> addresses = addressRepository.findAddressesByUserId(id);
                for (Address address: addresses) {
                    addressRepository.deleteById(address.getId());
                }
                userRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }



}
