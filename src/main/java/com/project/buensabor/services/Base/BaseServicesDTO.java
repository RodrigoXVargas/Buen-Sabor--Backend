package com.project.buensabor.services.Base;

import com.project.buensabor.dto.BaseDto;

import java.io.Serializable;
import java.util.List;

public interface BaseServicesDTO<F extends BaseDto, ID extends Serializable> {
    public List<F> findAll() throws Exception;

    public F findById(ID id) throws Exception;

    public F saveOne(F entity) throws Exception;

    public F updateOne(F entity, ID id) throws Exception;

    public boolean deleteById(ID id) throws Exception;
}
