package vanhoang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanhoang.entity.UserEntity;
import vanhoang.repository.mongodb.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser (String loginName) {
        UserEntity user = new UserEntity();
        user.setLoginName(loginName);
        userRepository.insert(user);
    }

    public UserEntity findUserByLoginName (String loginName) {
        return userRepository.findUserByLoginName(loginName);
    }

    public UserEntity findUserByEmail (String email) {
        return userRepository.findUserByEmail (email);
    }
}
