package vanhoang.dto;

import lombok.Data;
import vanhoang.dto.base.BaseDTO;

@Data
public class UserDTO extends BaseDTO {
    private String id;
    private String loginName;
}
