//package pj.hoangnv.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import pj.hoangnv.repository.entity.UserDocument;
//import pj.hoangnv.repository.impl.UserRepository;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDocument user = userRepository.findUserByUsername(username);
//        if(user == null)
//            user = userRepository.findUserByGmail(username);
//
//        if(user == null)
//            throw new UsernameNotFoundException("Tai khoan khong ton tai");
//
//        Set<GrantedAuthority> grants = new HashSet<>();
//        GrantedAuthority grant = new SimpleGrantedAuthority("ROLE_MEMBER");
//        grants.add(grant);
//
//        return new User(user.getUsername(), user.getPassword(), grants);
//    }
//}
