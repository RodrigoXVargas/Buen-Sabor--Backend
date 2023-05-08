package com.project.buensabor.services.Base;

import com.project.buensabor.entities.Base.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseServices<E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception;

    public Page<E> findAll(Pageable pageable) throws Exception;

    public E findById(ID id) throws Exception;

    public E saveOne(E entity) throws Exception;

    public E updateOne(E entity, ID id) throws Exception;

    public boolean deleteById(ID id) throws Exception;
}
