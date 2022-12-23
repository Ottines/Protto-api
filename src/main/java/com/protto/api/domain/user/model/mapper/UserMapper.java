package com.protto.api.domain.user.model.mapper;

import com.protto.api.data.entities.UserEntity;
import com.protto.api.domain.user.model.dao.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVO entityToUserVO(UserEntity userEntity);

}
