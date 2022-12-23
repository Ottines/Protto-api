package com.protto.api.data.repositories;

import com.protto.api.data.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = """
        select p
        from UserEntity p 
        where p.email = :email
    """)
    Optional<UserEntity> findByEmail(@Param("email")String email);
}
