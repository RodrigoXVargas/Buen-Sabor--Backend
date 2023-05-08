package com.project.buensabor.services;

import com.project.buensabor.dto.orderDto.StatusDto;
import com.project.buensabor.entities.Status;
import com.project.buensabor.repositories.StatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {


    private final StatusRepository statusRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<StatusDto> getAllCategories() {
        List<StatusDto> result = new ArrayList<>();
        List<Status> list = statusRepository.findAll();

        for (Status status : list) {
            StatusDto statusDto = StatusDto.builder().status(status.getStatus()).build();
            result.add(statusDto);
        }
        return result;
    }

    public StatusDto getStatus(Long statusID) {
        Optional<Status> status = statusRepository.findById(statusID);
        if(!(status.isEmpty())) {
            return StatusDto.builder().status(status.get().getStatus()).build();

        }
        return null;
    }

    public Status updateStatus(Status status, Long statusID) {
        Optional<Status> statusList = statusRepository.findById(statusID);
        if (statusList.get().getId_status() == status.getId_status()) {
            return statusRepository.save(status);
        } else {
            return null;
        }
    }

    public void deleteStatusById(Long statusID) {
        statusRepository.deleteById(statusID);
    }
}
