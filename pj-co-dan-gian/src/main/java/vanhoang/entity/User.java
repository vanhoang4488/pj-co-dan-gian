package vanhoang.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "users")
public class User {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private String loginName;
    private String email;
}
