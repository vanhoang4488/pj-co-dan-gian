package vanhoang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vanhoang.service.LdapService;
import vanhoang.service.UserService;

@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LdapService ldapService;

    private final UserService userService;

    @GetMapping("/ldap/login")
    public ResponseEntity<String> ldapLogin (@RequestParam("loginName") String loginName) {
        userService.addUser(loginName);
        System.out.println("-------------------------");
        return ResponseEntity.ok("Da hoan thanh dang nhap");
    }
}
