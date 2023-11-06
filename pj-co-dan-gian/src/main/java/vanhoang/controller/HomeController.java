package vanhoang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vanhoang.dto.base.ResultResponse;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResultResponse<String> home () {
        return ResultResponse.success("day la trang chu");
    }
}
