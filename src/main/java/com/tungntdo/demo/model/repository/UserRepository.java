package com.tungntdo.demo.model.repository;

import com.tungntdo.demo.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByEmail(final String email);
    UserEntity findByUserId(final String userId);
}
