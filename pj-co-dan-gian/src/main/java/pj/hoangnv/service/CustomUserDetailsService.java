package pj.hoangnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pj.hoangnv.entity.UserDocument;
import pj.hoangnv.repository.impl.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final String USER_NOT_FOUND_MSG =
            "Thông tin tài khoản hoặc mật khẩu không chính xác";

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        // tìm kiếm tài khoản theo gmail.
        UserDocument user = userRepository.findById(gmail)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));

        return user;
    }
}
