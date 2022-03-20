package com.softtech.bitirmeprojesiismaildemircann.app.gen.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.entity.BaseAdditionalFields;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.entity.BaseEntity;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.GenErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.ItemNotFoundException;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E, Long>> {

    private final D dao;

    private AuthenticationService authenticationService;

    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll(){
        List<E> list = dao.findAll();

        if(list == null) {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }

        return list;
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }

    public E save(E entity){

        setAdditionalFields(entity);
        entity = dao.save(entity);

        return entity;
    }

    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentUserId = authenticationService.getCurrentUserId();

        if(currentUserId == null) {
            currentUserId = 1L;
        }

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentUserId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentUserId);
    }

    public void delete(E entity){
        dao.delete(entity);
    }

    public E getByIdWithControl(Long id) {

        Optional<E> entityOptional = findById(id);

        E entity;
        if (entityOptional.isPresent()){
            entity = entityOptional.get();
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }

        return entity;
    }

    public boolean existsById(Long id){
        return dao.existsById(id);
    }

    public D getDao() {
        return dao;
    }

    public void deleteById(Long id) {

        if(existsById(id)) {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }

        dao.deleteById(id);
    }
}
