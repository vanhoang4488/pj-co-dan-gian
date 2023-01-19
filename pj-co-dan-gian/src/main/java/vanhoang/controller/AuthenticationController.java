package vanhoang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanhoang.service.LdapService;
import vanhoang.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LdapService ldapService;

    private final UserService userService;

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
}
