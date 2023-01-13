package vanhoang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanhoang.entity.User;
import vanhoang.repository.mongodb.UserRespository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;

    public void addUser (String loginName) {
        User user = new User();
        user.setLoginName(loginName);
        userRespository.insert(user);
    }
}
