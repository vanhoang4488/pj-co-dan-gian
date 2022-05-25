package pj.hoangnv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pj.hoangnv.repository.entity.UserDocument;
import pj.hoangnv.service.SysUserLoginService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/sys/user")
public class SysUserLoginController {

    @Autowired
    private SysUserLoginService sysUserLoginService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpServletRequest request,
                                        @RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam(required = false, defaultValue = "false") boolean rememberPwd){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        UserDocument user = sysUserLoginService.login(username, password, rememberPwd);
        return ResponseEntity.ok(user);
    }

    @RequestMapping("/login")
    public ResponseEntity<Object> login(){
        return ResponseEntity.ok("????????");
    }
}
