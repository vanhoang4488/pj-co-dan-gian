package vanhoang.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import vanhoang.entity.base.BaseEntity;

@Data
@Document(collection = "users_info")
public class UserInfoEntity extends BaseEntity {

    private String fullName;
    private String userLoginName;
}
