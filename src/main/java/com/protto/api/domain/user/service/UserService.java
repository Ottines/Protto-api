package com.protto.api.domain.user.service;

import com.protto.api.data.entities.UserEntity;
import com.protto.api.domain.user.model.dao.UserCreateRequest;

import com.protto.api.data.repositories.UserRepository;
import com.protto.api.domain.user.model.dao.UserVO;
import com.protto.api.domain.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Long create(UserCreateRequest userCreateRequest) {
        if(isAlreadyRegistered(userCreateRequest.email())) {
            throw new RuntimeException(); //exception perso a throw et pas du generic : exemple : throw new MyOwnRuntimeException("My Message");
        }
        UserEntity user = new UserEntity();
        user.setFirstName(userCreateRequest.firstName());
        user.setLastName(userCreateRequest.lastName());
        user.setEmail(userCreateRequest.email());
        user.setPassword(userCreateRequest.password());
        return userRepository.save(user).getId();
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<UserVO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::entityToUserVO)
                .toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UserVO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToUserVO)
                .orElseThrow(RuntimeException::new); //Modifier ca par une exception perso
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean isAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
