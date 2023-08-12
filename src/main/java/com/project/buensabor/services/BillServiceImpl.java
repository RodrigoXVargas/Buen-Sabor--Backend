package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.BillMapper;
import com.project.buensabor.dto.orderDto.BillDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.entities.Bill;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.BillRepository;
import com.project.buensabor.repositories.OrderProductsRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.BillService;
import com.project.buensabor.services.interfaces.OrderProductsService;
import com.project.buensabor.services.interfaces.OrderService;
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
public class BillServiceImpl extends BaseServicesDTOImpl<Bill, BillDto, BillMapper, Long> implements BillService {


    public BillServiceImpl(BaseRepository<Bill, Long> baseRepository, BillMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderService orderService;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<BillDto> findAll() throws Exception {
        try {
            List<Bill> bills = billRepository.findAll();
            List<BillDto> billDtos = new ArrayList<>();
            for (Bill bill : bills) {
                OrderDto orderDto = orderService.findById(bill.getOrder().getId());
                BillDto billDto = mapper.convertToDto(bill);
                billDto.setOrder(orderDto);
                billDtos.add(billDto);
            }
            return billDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public BillDto findById(Long id) throws Exception {
        try {
            Optional<Bill> billOptional = billRepository.findById(id);
            Bill bill = billOptional.get();
            OrderDto orderDto = orderService.findById(bill.getOrder().getId());
            BillDto billDto = mapper.convertToDto(bill);
            billDto.setOrder(orderDto);
            return billDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public BillDto saveOne(BillDto billDto) throws Exception {
        try {
            Bill bill = mapper.convertToEntity(billDto);
            bill = billRepository.save(bill);
            OrderDto orderDto = orderService.findById(bill.getOrder().getId());
            billDto = mapper.convertToDto(bill);
            billDto.setOrder(orderDto);
            return billDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public BillDto updateOne(BillDto billDto, Long id) throws Exception {
        try {
            Optional<Bill> billOptional = billRepository.findById(id);
            Bill bill = billOptional.get();
            modelMapper.map(billDto, bill);
            bill = billRepository.save(bill);
            OrderDto orderDto = orderService.findById(bill.getOrder().getId());
            billDto = mapper.convertToDto(bill);
            billDto.setOrder(orderDto);
            return billDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            if (billRepository.existsById(id)) {

                billRepository.deleteById(id);
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
