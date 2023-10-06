package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.MovementMapper;
import com.project.buensabor.dto.orderDto.MovementDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Movement;
import com.project.buensabor.enums.TypeMovement;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.MovementRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import com.project.buensabor.services.interfaces.MovementService;
import com.project.buensabor.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class MovementServiceImpl extends BaseServicesDTOImpl<Movement, MovementDto, MovementMapper, Long> implements MovementService {


    public MovementServiceImpl(BaseRepository<Movement, Long> baseRepository, MovementMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OrderService orderService;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<MovementDto> findAll() throws Exception {
        try {
            List<Movement> movements = movementRepository.findAll();
            List<MovementDto> movementDtos = new ArrayList<>();
            for (Movement movement : movements) {
                MovementDto movementDto = mapper.convertToDto(movement);
                if (Objects.nonNull(movement.getOrder())) {
                    OrderDto orderDto = orderService.findById(movement.getOrder().getId());
                    movementDto.setOrder(orderDto);
                }
                movementDtos.add(movementDto);
            }
            return movementDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovementDto findById(Long id) throws Exception {
        try {
            Optional<Movement> movementOptional = movementRepository.findById(id);
            Movement movement = movementOptional.get();
            MovementDto movementDto = mapper.convertToDto(movement);
            if (Objects.nonNull(movement.getOrder())) {
                OrderDto orderDto = orderService.findById(movement.getOrder().getId());
                movementDto.setOrder(orderDto);
            }
            return movementDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovementDto saveOne(MovementDto movementDto) throws Exception {
        try {
            Movement movement = mapper.convertToEntity(movementDto);
            movement = movementRepository.save(movement);
            movementDto = mapper.convertToDto(movement);
            if (Objects.nonNull(movement.getOrder())) {
                OrderDto orderDto = orderService.findById(movement.getOrder().getId());
                movementDto.setOrder(orderDto);
            }
            return movementDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovementDto updateOne(MovementDto movementDto, Long id) throws Exception {
        try {
            Optional<Movement> movementOptional = movementRepository.findById(id);
            Movement movement = movementOptional.get();
            modelMapper.map(movementDto, movement);
            movement = movementRepository.save(movement);
            movementDto = mapper.convertToDto(movement);
            if (Objects.nonNull(movement.getOrder())) {
                OrderDto orderDto = orderService.findById(movement.getOrder().getId());
                movementDto.setOrder(orderDto);
            }
            return movementDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            if (movementRepository.existsById(id)) {

                movementRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<MovementDto> getMovementsByDates(LocalDate desde, LocalDate hasta, String type) throws Exception {
        try{
            type = type+"%";
            List<Movement> movementList = movementRepository.getMovementsByDateBetween(desde, hasta, type);
            List<MovementDto> movementDtoList = new ArrayList<>();
            for (Movement movement: movementList) {
                movementDtoList.add(mapper.convertToDto(movement));
            }
            return movementDtoList;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void saveRestoking(List<PIngredientsCantDto> ingredientList) throws Exception {
        try{
            double total = 0;
            for (PIngredientsCantDto ingredientsCantDto: ingredientList) {
                IngredientDto ingredient = ingredientService.findById(ingredientsCantDto.getIngredient().getId());
                total += ingredient.getCost() * ingredientsCantDto.getCant();
            }
            Movement movement = new Movement(
                    TypeMovement.Restocking,
                    dateService.dateNow(),
                    "Restocking",
                    total*-1,
                    null);
            movement = movementRepository.save(movement);

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
