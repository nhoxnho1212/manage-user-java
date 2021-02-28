package com.tungntdo.demo.model.repository;

import com.tungntdo.demo.model.entity.TokenTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenTypeRepository extends CrudRepository<TokenTypeEntity, Long> {
    TokenTypeEntity findTokenTypeEntityByTypeName(final String typeName);
}
