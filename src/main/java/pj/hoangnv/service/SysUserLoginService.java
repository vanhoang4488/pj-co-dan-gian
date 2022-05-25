package pj.hoangnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pj.hoangnv.repository.entity.UserDocument;
import pj.hoangnv.repository.impl.UserRepository;

@Service
public class SysUserLoginService {

    @Autowired
    private UserRepository userRepository;

    public UserDocument login(String username, String password, boolean rememberPwd){
        return userRepository.findUserByGmail(username);
    }
}
