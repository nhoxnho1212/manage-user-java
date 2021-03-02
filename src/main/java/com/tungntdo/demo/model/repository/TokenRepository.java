package com.tungntdo.demo.model.repository;

import com.tungntdo.demo.model.entity.TokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, Long> {
    TokenEntity findTokenEntityByTokenId(String tokenId);
}
