package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.UserMapper;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
