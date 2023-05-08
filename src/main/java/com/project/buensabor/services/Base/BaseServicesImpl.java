package com.project.buensabor.services.Base;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.repositories.Base.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
    public abstract class BaseServicesImpl<E extends Base, ID extends Serializable> implements BaseServices<E, ID> {

        protected BaseRepository<E, ID> baseRepository;

        public BaseServicesImpl(BaseRepository<E, ID> baseRepository) {
            this.baseRepository = baseRepository;
        }

        @Override
        @Transactional //Indica que el método es una transacción.
        public List<E> findAll() throws Exception {
            try {
                List<E> entities = baseRepository.findAll();
                return entities;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new Exception(e.getMessage());
            }
        }

        @Override
        @Transactional
        public Page<E> findAll(Pageable pageable) throws Exception {
            try {
                Page<E> entities = baseRepository.findAll(pageable);
                return entities;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new Exception(e.getMessage());
            }
        }

        @Override
        @Transactional
        public E findById(ID id) throws Exception {
            try {
                Optional<E> entityOptional = baseRepository.findById(
                        id); //Optional porque no se sabe si se encontrará un registro que tenga el ID especificado como PrimaryKey.
                return entityOptional.get();
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new Exception(e.getMessage());
            }
        }

        @Override
        @Transactional
        public E saveOne(E entity) throws Exception {
            try {
                entity = baseRepository.save(entity);
                return entity;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new Exception(e.getMessage());
            }
        }

        @Override
        @Transactional
        public E updateOne(E entity, ID id) throws Exception {
            try {
                Optional<E> entityOptional = baseRepository.findById(id);
                E entityUpdate = entityOptional.get();
                entityUpdate = baseRepository.save(entity);
                return entityUpdate;
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

