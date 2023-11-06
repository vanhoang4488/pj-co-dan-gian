package vanhoang.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import vanhoang.entity.base.BaseEntity;

@Data
@Document(collection = "users")
public class UserEntity extends BaseEntity {

    private String loginName;
    private String email;
}
