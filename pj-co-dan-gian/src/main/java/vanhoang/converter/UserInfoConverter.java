package vanhoang.converter;

import org.mapstruct.Mapper;
import vanhoang.dto.UserInfoDTO;
import vanhoang.entity.UserInfoEntity;

@Mapper
public interface UserInfoConverter {

    UserInfoEntity userInfoDTOToUserInfoEntity(UserInfoDTO userInfoDTO);
}
