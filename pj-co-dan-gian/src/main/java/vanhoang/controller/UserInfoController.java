package vanhoang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vanhoang.dto.UserInfoDTO;
import vanhoang.dto.base.ResultResponse;
import vanhoang.entity.UserInfoEntity;
import vanhoang.service.UserInfoService;

@RestController
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;
    @PostMapping("/users_info")
    public ResultResponse<Object> addUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        UserInfoEntity userInfoEntity = userInfoService.addUserInfo(userInfoDTO);
        if(userInfoEntity == null) return ResultResponse.failed();
        else return ResultResponse.success();
    }

    @GetMapping("/users_info")
    public ResultResponse<Object> findUserInfo() {
        return ResultResponse.success();
    }
}
