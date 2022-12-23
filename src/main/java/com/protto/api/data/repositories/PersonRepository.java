package com.protto.api.data.repositories;


import com.protto.api.data.entities.PersonEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query(value = """
        select p
        from PersonEntity p 
        where p.email = :email
    """)
    Optional<PersonEntity> findByEmail(@Param("email")String email);
}
