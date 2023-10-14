package com.project.buensabor.services.Base;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.repositories.Base.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class BaseServicesDTOImpl<E extends Base, F extends BaseDto, M extends ModelMapperEntity<E, F>, ID extends Serializable> implements BaseServicesDTO<F, ID> {

    protected BaseRepository<E, ID> baseRepository;
    protected ModelMapperEntity<E, F> mapper;

    public BaseServicesDTOImpl(BaseRepository<E, ID> baseRepository, M mapper) {
        this.baseRepository = baseRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional //Indica que el método es una transacción.
    public List<F> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            List<F> entitiesDtos = new ArrayList<>();
            for (E entity : entities) {
                entitiesDtos.add(mapper.convertToDto(entity));
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public F findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return mapper.convertToDto(entityOptional.get());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public F saveOne(F entityDto) throws Exception {
        try {
            E entity = mapper.convertToEntity(entityDto);
            entity = baseRepository.save(entity);
            return mapper.convertToDto(entity);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public F updateOne(F entity, ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(mapper.convertToEntity(entity));
            return mapper.convertToDto(entityUpdate);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
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
