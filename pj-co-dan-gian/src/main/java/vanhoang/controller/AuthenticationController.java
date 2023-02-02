package vanhoang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vanhoang.entity.User;
import vanhoang.service.LdapService;
import vanhoang.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LdapService ldapService;

    private final UserService userService;

    @GetMapping("/google/login")
    public ResponseEntity<User> googleLogin (HttpServletRequest request){
        Enumeration<String> headerNames =  request.getHeaderNames();
        Iterator<String> headerNameIterator = headerNames.asIterator();
        while (headerNameIterator.hasNext()) {
            String headerName = headerNameIterator.next();
            log.info("header: {} = {}", headerName, request.getHeader(headerName));
        }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        log.info("======> authentication: {}, {}, {}", authentication.isAuthenticated(), authentication.getName(), (String) authentication.getPrincipal());
        log.info("====> account authenticated successfulley: {}", authentication.getName());
        return ResponseEntity.ok(userService.findUserByEmail(authentication.getName()));
    }

    @GetMapping("/register")
    public ResponseEntity<String> register (@RequestParam("loginName") String loginName,
                                            @RequestParam("password") String password,
                                            @RequestParam("confirmPassword") String confirmPassword) {
        if (!password.equals(confirmPassword))
            return ResponseEntity.ok("confirm password khong khop");

        ldapService.createUser(loginName, password);

        userService.addUser(loginName);

        return ResponseEntity.ok("tao tai khoan thanh cong");
    }

    @PostMapping("/error")
    public ResponseEntity<String> error () {
        log.info("=======> user xac thuc that bai");
        return ResponseEntity.ok("xac thuc that bai");
    }
}
