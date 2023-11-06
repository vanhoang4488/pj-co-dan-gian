package vanhoang.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import vanhoang.converter.UserInfoConverter;
import vanhoang.dto.UserInfoDTO;
import vanhoang.entity.UserInfoEntity;
import vanhoang.repository.mongodb.UserInfoRepository;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoConverter userInfoConverter = Mappers.getMapper(UserInfoConverter.class);
    private final UserInfoRepository userInfoRepository;
    public UserInfoEntity addUserInfo(UserInfoDTO userInfoDTO) {
        UserInfoEntity userInfoEntity = userInfoConverter.userInfoDTOToUserInfoEntity(userInfoDTO);
        userInfoEntity = userInfoRepository.insert(userInfoEntity);
        return userInfoEntity;
    }
}
