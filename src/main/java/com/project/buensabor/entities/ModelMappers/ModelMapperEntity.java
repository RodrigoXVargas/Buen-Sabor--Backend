package com.project.buensabor.entities.ModelMappers;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.entities.Location;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class ModelMapperEntity<E extends Base, F extends BaseDto>{

    private ModelMapper modelMapper;

    public ModelMapperEntity() {
        this.modelMapper = new ModelMapper();
    }

    public F convertToDto(E entity) {
        return modelMapper.map(entity, getTypeDtoClass());
    }

    public E convertToEntity(F dto) {
        return modelMapper.map(dto, getTypeEntityClass());
    }

    private Class<E> getTypeEntityClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            return (Class<E>) paramType.getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("Cannot determine entity type.");
        }
    }

    private Class<F> getTypeDtoClass() {
        Type type2 = getClass().getGenericSuperclass();
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            return (Class<F>) paramType.getActualTypeArguments()[1];
        } else {
            throw new IllegalArgumentException("Cannot determine DTO type.");
        }
    }

}


