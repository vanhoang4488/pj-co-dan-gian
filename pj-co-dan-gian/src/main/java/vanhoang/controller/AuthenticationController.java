package vanhoang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanhoang.entity.User;
import vanhoang.service.LdapService;
import vanhoang.service.UserService;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LdapService ldapService;

    private final UserService userService;

    @GetMapping("/google/login")
    public ResponseEntity<User> googleLogin (@RequestParam("email") String email){
        log.info("=====> google authentication successfully: {}", email);
        return ResponseEntity.ok(userService.findUserByEmail(email));
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
        log.info("user xac minh that bai");
        return ResponseEntity.ok("xac thuc that bai");
    }
}
