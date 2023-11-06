package vanhoang.dto;

import lombok.Data;
import vanhoang.dto.base.BaseDTO;

@Data
public class UserInfoDTO extends BaseDTO {

    private String id;
    private String fullName;
    private String userLoginName;
}
