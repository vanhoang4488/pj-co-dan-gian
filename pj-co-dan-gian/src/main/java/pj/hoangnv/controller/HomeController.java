package pj.hoangnv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public ResponseEntity<Object> home(){
        return ResponseEntity.ok("home");
    }
}
