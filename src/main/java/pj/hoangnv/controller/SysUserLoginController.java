package pj.hoangnv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pj.hoangnv.entity.UserDocument;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/sys/user")
public class SysUserLoginController {

    @RequestMapping("/login")
    public ResponseEntity<Object> login(){
        return ResponseEntity.ok("????????");
    }
}
